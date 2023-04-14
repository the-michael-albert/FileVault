import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from getpass import getpass



# Email parameters
from_addr = 'contact@michaelalbert.one'
to_addr = 'malber@uw.edu'
subject = 'Test Email'
body = '<html></html>'

# SMTP server configuration
smtp_server = 'smtp.office365.com'
smtp_port = 587
smtp_username = 'contact@michaelalbert.one'
smtp_password = getpass('Enter your password: ')

# Create message container
msg = MIMEMultipart()
msg['From'] = from_addr
msg['To'] = to_addr
msg['Subject'] = subject

# Add message body
msg.attach(MIMEText(body, 'plain'))

# Create SMTP session and encrypt with STARTTLS
smtp_session = smtplib.SMTP(smtp_server, smtp_port)
smtp_session.starttls()

# Login to SMTP server
smtp_session.login(smtp_username, smtp_password)

# Send email
smtp_session.sendmail(from_addr, to_addr, msg.as_string())

# Quit SMTP session
smtp_session.quit()

print('Email sent successfully.')
