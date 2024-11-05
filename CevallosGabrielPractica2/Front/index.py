from app import create_app
#from flask import session

app = create_app()
if __name__ == "__main__":
    app.secret_key = 'feirnnr2024'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(debug=True, host='0.0.0.0')