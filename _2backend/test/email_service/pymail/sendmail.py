import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage

# Set up the SMTP server
smtp_server = "smtpout.secureserver.net"
port = 465 # SSL port

# Set up the login credentials
username = "contact@michaelalbert.one"
password = "password123"

# Set up the email contents
sender = "contact@michaelalbert.one"
recipient = "recipient@example.com"
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
with open("image.jpg", "rb") as f:
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

# Send the email
with smtplib.SMTP_SSL(smtp_server, port) as server:
    server.login(username, password)
    server.sendmail(sender, recipient, msg.as_string())
