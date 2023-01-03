package com.isep;

import java.util.ArrayList;

public class Halicarnassus extends Wonder
{
    // Constructor
    public Halicarnassus()
    {
        super("Halicarnassus", new ArrayList<Stage>() {{
            add(new Stage(3, 2, false, false, false, 1));
            add(new Stage(3, 2, true, true, false, 2));
            add(new Stage(6, 3, false, false, false, 3));
            add(new Stage(5, 3, true, true, false, 3));
            add(new Stage(7, 4, false, false, false, 4));
        }});
    }
}
