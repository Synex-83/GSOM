/**
 * 
 */
package com.test.image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class ThirdClass implements ActionListener {

BufferedImage mock;

public ThirdClass(BufferedImage img)
{
    mock = img;
    Train();
}

public void Train()
{
  //  mock.render();
}

@Override
public void actionPerformed(ActionEvent arg0) {
    Train();
}

    }
