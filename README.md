# J & J's Image Factory :paintbrush: :paintbrush:
Jiayang and Johnson's Fall Final Project!!!  
https://github.com/jchen59/J-J-s-Image-Factory

## About
An image editor to edit existing images or create new images from scratch with tools like:  
    -Pencil and Eraser  
    -Paintbrush  
    -Rectangle and Ellipse  
    -Paintbucket  
We have implemented special filters including:  
    -Grayscale  
    -Basic Edge Detection  
    -Invert Colors  

## How to Use
1) Compile Main.java  
2) Run Main: java Main [optional path of image]  
3) Open/Save images with the menu at the top.    
4) Select a tool from the right side of the window. Select color from the color button. Size can be changed for certain tools using the slider.  
5) Edit images to your hearts desire!  
6) Make sure to save using an image file extension.   

## Development Log
bold = milestone    
1/3/18 - Initial commit. Made Image open in window using the command line.  
1/5/18 - **Succeeded in using file chooser to open images.**     
1/6/18 - Made first tool (pencil). Also made a null tool that doesn't do anything as default tool.  
1/7/18 - **Got pencil to work.**  
1/10/18 - Made basic color selection buttons.  
1/11/18 - Started to work on filters.  
1/13/18 - **Eraser works.** (erases everything including the image)  
1/14/18 - **Got first filter, gray scale, to work.**  
1/16/18 - **added two more filters: invert colors, and edge detect.**  
1/17/18 - Changed the structures of class by making Tools and Filters abstract. **Brush size added.**  
1/18/18 - Added a small sentences about the tools at the bottom of the window.  
1/19/18 - **Ellipse and rectangle tool added. Info box added. Reset Image button works. Checkbox for sharp shapes added.**      
1/20/18 - Code was cleaned up. Icons added to tools.  
1/21/18 - **Paint bucket tool added.**  

## Future Plans
* Make image open in the center and make image fit in window regardless of size.  
* Make a non-global paint bucket to only color a selected region.
* Add zooming in and out

## Known Bugs
* If the window is too small, the tools on the bottom of the program do not resize and do not show.
* If the image is larger than the window, the image will be cut off until you make the window larger.
