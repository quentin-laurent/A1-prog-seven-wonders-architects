package com.isep;

import java.util.ArrayList;

public class Giza extends Wonder
{
    // Constructor
    public Giza()
    {
        super("Giza", new ArrayList<Stage>() {{
            add(new Stage(4, 2, false, false, false, 1));
            add(new Stage(5, 2, true, false, false, 2));
            add(new Stage(6, 3, false, false, false, 3));
            add(new Stage(7, 3, true, false, false, 4));
            add(new Stage(8, 4, false, false, false, 5));
        }});
    }
}
