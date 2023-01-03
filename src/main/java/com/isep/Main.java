package com.isep;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Alexandria alexandria = new Alexandria();
        List<Stage> stages = alexandria.getStages();
        for(Stage stage: stages)
            System.out.println(stage);
    }
}