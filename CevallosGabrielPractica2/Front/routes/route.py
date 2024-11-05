from flask import Blueprint, abort, render_template, redirect, flash, request, url_for
import json
import requests
router = Blueprint('router', __name__)

@router.route('/')
def home():

    return render_template('fragmento/inicial/admin.html')  


@router.route('/admin')
def admin():

    return render_template('fragmento/inicial/admin.html') 


@router.route('/admin/inversionista/all')
def list_inversionista(msg=''):
    r = requests.get('http://localhost:8077/sgpe/inversionista/all')
    inversionistas = r.json()["informacion"]
    i = 1
    for inversionista in inversionistas:
        inversionista['numero'] = i
        i+=1
    return render_template('fragmento/inversionista/listaInversionista.html', inversionistas = inversionistas)


@router.route('/admin/proyecto/all')
def list_proyecto():
    r = requests.get('http://localhost:8077/sgpe/proyectoEnergia/all')
    proyectos = r.json()["informacion"]
    i = 1
    for proyecto in proyectos:
        s = requests.get('http://localhost:8077/sgpe/inversionista/get/'+str(proyecto['idInversionista']))
        proyecto['inversionista'] = s.json()['informacion']['nombre']
        proyecto['numero'] = i
        i+=1
    return render_template('fragmento/proyectos/listaProyectos.html', proyectos = proyectos)



@router.route('/admin/inversionista/register')
def view_register_inversionista():
    r = requests.get("http://localhost:8077/sgpe/inversionista/type")
    data = r.json()["informacion"]
    return render_template('fragmento/inversionista/registroInversionista.html', list = data)


@router.route('/admin/proyecto/register')
def view_register_proyecto():

    return render_template('fragmento/proyectos/registroProyectos.html')



@router.route('/admin/inversionista/edit/<id>')
def view_edit_inversionista(id):
    r = requests.get("http://localhost:8077/sgpe/inversionista/type")
    data = r.json()["informacion"]
    r1 = requests.get("http://localhost:8077/sgpe/inversionista/get/"+id)
    data1 = r1.json()["informacion"]
    if(r1.status_code == 200):
        return render_template('fragmento/inversionista/editarInversionista.html', list = data, inversionista=data1)
    else:
        flash(data1, category='error')
        return redirect("/admin/inversionista/all")


@router.route('/admin/proyecto/edit/<id>')
def view_edit_proyecto(id):
    r = requests.get("http://localhost:8077/sgpe/proyectoEnergia/get/"+id)
    data = r.json()["informacion"]
    if(r.status_code == 200):
        return render_template('fragmento/proyectos/editarProyectos.html', proyecto = data)
    else:
        flash(data, category='error')
        return redirect("/admin/proyecto/all")
    


@router.route('/admin/inversionista/save', methods=['POST'])
def save_inversionista():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombre": form['nom'],
        "apellido": form['ape'],
        "tipoIdentificacion": form['tipoI'],
        "dni": form['dn'],
        "direccion": form['dir'],
        "capitalInvertido": form['capInv'],
    }

    r = requests.post("http://localhost:8077/sgpe/inversionista/save", data=json.dumps(dataF), headers=headers)
    
    dat = r.json()
    if r.status_code == 200:
        flash("¡Se ha guardado correctamente!", category='info')
        return redirect("/admin/inversionista/all")
    else:
        flash(str(dat["informacion"]), category='error')
        return redirect("/admin/inversionista/all")
    

@router.route('/admin/proyecto/save', methods=['POST'])
def save_proyecto():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombre": form['nom'],
        "ubicacion": form['ubi'],
        "montoTotalInversion": form['monTotalInv'],
        "cantidadElectricidad": form['cantElec'],
        "fechaInicio": form['fechaI'],
        "fechaFin": form['fechaF'],
        "idInversionista": form['idInv'],
    }

    r = requests.post("http://localhost:8077/sgpe/proyectoEnergia/save", data=json.dumps(dataF), headers=headers)
    
    dat = r.json()
    if r.status_code == 200:
        flash("¡Se ha guardado correctamente!", category='info')
        return redirect("/admin/proyecto/all")
    else:
        flash(str(dat["informacion"]), category='error')
        return redirect("/admin/proyecto/all")
    


@router.route('/admin/inversionista/update', methods=['POST'])
def update_inversionista():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "idInversionista": form['idInv'],
        "nombre": form['nom'],
        "apellido": form['ape'],
        "tipoIdentificacion": form['tipoI'],
        "dni": form['dn'],
        "direccion": form['dir'],
        "capitalInvertido": form['capInv'],
    }

    r = requests.post("http://localhost:8077/sgpe/inversionista/update", data=json.dumps(dataF), headers=headers)
    
    dat = r.json()
    if r.status_code == 200:
        flash("¡Se ha actualizado correctamente!", category='info')
        return redirect("/admin/inversionista/all")
    else:
        flash(str(dat["informacion"]), category='error')
        return redirect("/admin/inversionista/all")
    

@router.route('/admin/proyecto/update', methods=['POST'])
def update_proyecto():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "idProyecto" : form['idPr'],
        "nombre": form['nom'],
        "ubicacion": form['ubi'],
        "montoTotalInversion": form['monTotalInv'],
        "cantidadElectricidad": form['cantElec'],
        "fechaInicio": form['fechaI'],
        "fechaFin": form['fechaF'],
        "idInversionista": form['idInv'],
    }
    
    r = requests.post("http://localhost:8077/sgpe/proyectoEnergia/update", data=json.dumps(dataF), headers=headers)
    
    if r.status_code == 200:
        flash("¡Se ha actualizado correctamente!", category='info')
        return redirect("/admin/proyecto/all")
    else:
        flash("¡No se ha podido actualizar!", category='error')
        return redirect("/admin/proyecto/all")


@router.route('/admin/inversionista/eliminar/<id>')
def view_delete_inversionista(id):
    r = requests.post("http://localhost:8077/sgpe/inversionista/delete/"+id)
    data = r.json()
    if(r.status_code == 200):
        return render_template('fragmento/inversionista/deleteInversionista.html', inversionista = data)
    else:
        flash(data, category='error')
        return redirect("/admin/inversionista/all")
    

@router.route('/admin/proyecto/eliminar/<id>')
def view_delete_proyecto(id):
    r = requests.post("http://localhost:8077/sgpe/proyectoEnergia/delete/"+id)
    data = r.json()
    if(r.status_code == 200):
        return render_template('fragmento/proyectos/deleteProyectos.html', proyecto = data)
    else:
        flash(data, category='error')
        return redirect("/admin/proyecto/all")


@router.route('/admin/proyecto/delete', methods=['POST'])
def delete_proyecto():
    json = request.form.to_dict()
    headers = {'Content-Type': 'application/json'}
    r = requests.post('http://localhost:8077/sgpe/proyectoEnergia/delete', json=json, headers=headers)
    
    if r.status_code == 200:
        flash("¡Se ha eliminado correctamente!", category='info')
        return redirect("/admin/proyecto/all")
    else:
        
        return redirect("/admin/proyecto/all")
    

@router.route('/admin/inversionista/delete', methods=['POST'])
def delete_inversionista():
    json = request.form.to_dict()
    headers = {'Content-Type': 'application/json'}
    r = requests.post('http://localhost:8077/sgpe/inversionista/delete', json=json, headers=headers)
    
    if r.status_code == 200:
        flash("¡Se ha eliminado correctamente!", category='info')
        return redirect("/admin/inversionista/all")
    else:
        flash("¡No se ha podido eliminar!", category='error')
        return redirect("/admin/inversionista/all")
    