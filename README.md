# jTessBoxEditor

This is a fork of a part of [VietOCR](http://vietocr.sourceforge.net/training.html) project.

jTessBoxEditor is a cross platform tool for editing .box files used for training [Tesseract OCR](https://code.google.com/p/tesseract-ocr/). The original application required too many mouse movements and clicks, so I added hotkeys and better box editor widget.

### Improvements

- Scaling and display of surrounding pixels for scaled box view
- Adjustable box scaling and margins
- Hotkeys for editing box positions and sizes


### Screenshot
![Screenshot](https://raw.githubusercontent.com/A2K/jTessBoxEditor/master/screenshot.png)


### Hotkeys
- **W** - move box up
- **S** - move box down
- **A** - move box left
- **D** - move box right
- **Q** - decrease box width
- **E** - increase box width
- **R** - decrease box height
- **F** - increase box height
- **<** - previous box
- **>** - next box
- **X** - edit character in box

Holding shift when using hotkeys multiplies movement speed by 10.

Pressing Enter or ESC when editing character focuses the box editor.

Box movement controls can be inverted via a checkbox in editor panel.
