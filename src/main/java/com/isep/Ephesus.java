package com.isep;

import java.util.ArrayList;

public class Ephesus extends Wonder
{
    // Constructor
    public Ephesus()
    {
        super("Ephesus", new ArrayList<Stage>() {{
            add(new Stage(3, 2, false, false, false, 1));
            add(new Stage(3, 2, true, true, false, 2));
            add(new Stage(4, 3, false, true, false, 2));
            add(new Stage(5, 3, true, true, false, 2));
            add(new Stage(7, 4, false, false, false, 3));
        }});
    }
}
