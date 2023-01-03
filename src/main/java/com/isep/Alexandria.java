package com.isep;

import java.util.ArrayList;

public class Alexandria extends Wonder
{
    // Constructor
    public Alexandria()
    {
        super("Alexandria", new ArrayList<Stage>() {{
            add(new Stage(4, 2, false, false, false, 1));
            add(new Stage(3, 2, true, true, false, 2));
            add(new Stage(6, 3, false, false, false, 3));
            add(new Stage(5, 3, true, true, false, 4));
            add(new Stage(7, 4, false, false, false, 5));
        }});
    }
}
