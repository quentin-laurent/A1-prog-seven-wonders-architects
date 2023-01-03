package com.isep;

import java.util.ArrayList;

public class Rhodes extends Wonder
{
    // Constructor
    public Rhodes()
    {
        super("Rhodes", new ArrayList<Stage>() {{
            add(new Stage(4, 2, false, false, false, 1));
            add(new Stage(4, 2, true, true, false, 1));
            add(new Stage(5, 3, false, false, false, 2));
            add(new Stage(6, 3, true, true, false, 3));
            add(new Stage(7, 4, false, false, false, 4));
        }});
    }
}
