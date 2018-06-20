from functools import wraps

from flask import Blueprint, render_template, abort, redirect, url_for, flash, session
from flask_login import current_user, logout_user, login_user, login_required

from .forms import LoginForm, ReservationForm
from .models import User, Accomodation, Reservation, VIP, AvailableRooms

bp = Blueprint('main', __name__)


def staff_required(func):
    @wraps(func)
    def decorated_view(*args, **kwargs):
        if not current_user.staff:
            abort(403)
        return func(*args, **kwargs)
    return decorated_view


@bp.route('/', methods=['GET', 'POST'])
@bp.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        user = User.query.filter_by(username=form.username.data).first()
        if user is None or not user.check_password(form.password.data):
            flash('Invalid username or password', 'error')
            return redirect(url_for('main.login'))
        login_user(user, remember=form.remember.data)
        return redirect('/home')
    return render_template('login.html', form=form)


@bp.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect('/')


@bp.route('/home')
@login_required
def home():
    accomodations = current_user.accomodations
    if len(accomodations) == 1:
        return redirect(url_for('main.accomodation', name=accomodations[0].name))
    elif len(accomodations) > 1:
        return render_template('accomodation_list.html', accomodations=accomodations)
    else:
        return redirect(url_for('main.reservation'))


@bp.route('/accomodations')
@login_required
def accomodations():
    if current_user.staff:
        accomodations = Accomodation.query.all()
        return render_template('accomodation_list.html', accomodations=accomodations)
    elif current_user.accomodations:
        return redirect(url_for('main.home'))
    else:
        abort(403)


@bp.route('/accomodations/<name>', methods=['GET', 'POST'])
@login_required
def accomodation(name):
    accomodation = Accomodation.query.filter_by(name=name).first_or_404()
    form = AccomodationForm()
    if form.validate_on_submit():
        pass
        flash('The changes have successfully been saved', 'success')
    if accomodation in current_user.accomodations:
        return render_template('update_accomodation.html', accomodation=accomodation,
                               form=form)
    else:
        return render_template('accomodation.html', accomodation=accomodation)


@bp.route('/reservation', methods=['GET', 'POST'])
@login_required
@staff_required
def reservation():
    form = ReservationForm()
    vips = VIP.query.order_by('last_name')
    form.vips.choices = [(vip.id, f'{vip.last_name} {vip.first_name}') for vip in vips]
    if form.validate_on_submit():
        vips = []
        for vip_id in form.vips.data:
            vips.append(VIP.query.filter_by(id=vip_id))
        nb_vips = len(vips)
        accomodations = Accomodation.query.all()
        available_accomodations = [acco for acco in accomodations \
                                    if acco.is_available(form.date_arrival.data,
                                                         form.date_departure.data,
                                                         nb_vips)]
        if not available_accomodations:
            flash('No more rooms are available', 'error')
            return redirect(url_for('main.reservation'))

        session['reservation'] = Reservation(date_arrival=form.date_arrival.data,
                                             date_departure=form.date_departure.data,
                                             vip=vips)
        return render_template('choose_accomodation.html',
                               accomodations=available_accomodations)
    return render_template('reservation.html', form=form)
