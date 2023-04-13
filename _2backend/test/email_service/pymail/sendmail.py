import smtplib
import sys
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage
import getpass

# Set up the SMTP server
smtp_server = "smtpout.secureserver.net"
port = 465 # SSL port

# Set up the login credentials
username = "contact@michaelalbert.one"

# Set up the email contents
sender = "contact@michaelalbert.one"
recipient = "malber@uw.edu"
subject = "Example HTML email"
html = """\
<html>
  <body>
    <h1>This is an example HTML email!</h1>
    <p>Here is some content:</p>
    <ul>
      <li>Item 1</li>
      <li>Item 2</li>
      <li>Item 3</li>
    </ul>
    <img src="cid:image1">
  </body>
</html>
"""
# Load an image file to attach as an inline image
with open("image.png", "rb") as f:
    image_data = f.read()
image = MIMEImage(image_data)
image.add_header('Content-ID', '<image1>')

# Create the message object and set its attributes
msg = MIMEMultipart()
msg['From'] = sender
msg['To'] = recipient
msg['Subject'] = subject
msg.attach(MIMEText(html, 'html'))
msg.attach(image)

print("hi");

password = getpass.getpass("pass:", stream=sys.stdin)
print(password)
# Send the email
with smtplib.SMTP_SSL(smtp_server, port) as server:
    server.login(username, password)
    server.sendmail(sender, recipient, msg.as_string())
