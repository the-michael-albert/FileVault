import cv2
import numpy as np
import torch
import torchvision.transforms as transforms

# Define the super resolution model and load its pre-trained weights
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(device)
model = torch.hub.load('pytorch/vision', 'srgan', pretrained=True)
model.to(device).eval()

# Define a function to apply super resolution to an input image
def apply_super_resolution(img):
    # Convert the input image to YCbCr color space and extract the Y channel
    img_ycbcr = cv2.cvtColor(img, cv2.COLOR_BGR2YCrCb)
    img_y = img_ycbcr[:, :, 0]

    # Resize the input image to a lower resolution using bicubic interpolation
    transform = transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.5], std=[0.5]),
        transforms.Resize((img_y.shape[0] // 4, img_y.shape[1] // 4),
                           interpolation=transforms.InterpolationMode.BICUBIC),
    ])
    img_y_downsampled = transform(img_y).unsqueeze(0).to(device)

    # Apply super resolution to the downsampled Y channel using the pre-trained model
    with torch.no_grad():
        img_y_upsampled_sr = model(img_y_downsampled).data.squeeze().cpu().numpy()

    # Rescale the super resolved Y channel back to its original size
    img_y_upsampled = cv2.resize(img_y_upsampled_sr,
                                 dsize=(img_y.shape[1], img_y.shape[0]),
                                 interpolation=cv2.INTER_CUBIC)

    # Convert the super resolved Y channel back to BGR color space
    img_ycbcr_upsampled = np.zeros_like(img_ycbcr)
    img_ycbcr_upsampled[:, :, 0] = img_y_upsampled
    img_ycbcr_upsampled[:, :, 1:] = img_ycbcr[:, :, 1:]
    img_rgb_upsampled_sr = cv2.cvtColor(img_ycbcr_upsampled, cv2.COLOR_YCrCb2BGR)

    # Apply post-processing filters to the super resolved image (optional)
    img_rgb_upsampled_sr = cv2.bilateralFilter(img_rgb_upsampled_sr, d=3, sigmaColor=50, sigmaSpace=50)
    img_rgb_upsampled_sr = cv2.GaussianBlur(img_rgb_upsampled_sr, ksize=(5,5), sigmaX=2, sigmaY=2)

    # Clip pixel values to the valid range [0, 255]
    img_rgb_upsampled_sr = np.clip(img_rgb_upsampled_sr, 0, 255).astype(np.uint8)

    return img_rgb_upsampled_sr

# Test the super resolution algorithm on an input image
img = cv2.imread('input.png')
img_rgb_upsampled_sr = apply_super_resolution(img)

# Save the super resolved image to disk
cv2.imwrite('output.png', img_rgb_upsampled_sr)
