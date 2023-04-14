import getpass

from flask import Flask, request
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

app = Flask(__name__)

@app.route('/send-email', methods=['POST'])
def send_email():
    # Get email parameters from JSON request body
    email_data = request.get_json()
    subject = email_data['subject']
    body = email_data['body']

    # Get email addresses from URL parameters
    from_addr = email_data['from']
    to_addr = email_data['to']

    # SMTP server configuration
    smtp_server = 'smtp.office365.com'
    smtp_port = 587
    smtp_username = 'contact@michaelalbert.one'
    smtp_password = getpass.getpass("password:")

    # Create message container
    msg = MIMEMultipart()
    msg['From'] = from_addr
    msg['To'] = to_addr
    msg['Subject'] = subject

    # Add message body
    msg.attach(MIMEText(body, email_data['type']))

    # Create SMTP session and encrypt with STARTTLS
    smtp_session = smtplib.SMTP(smtp_server, smtp_port)
    smtp_session.starttls()

    # Login to SMTP server
    smtp_session.login(smtp_username, smtp_password)

    # Send email
    smtp_session.sendmail(from_addr, to_addr, msg.as_string())

    # Quit SMTP session
    smtp_session.quit()

    return 'Email sent successfully.'

if __name__ == '__main__':
    app.run()
