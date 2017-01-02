package com.example.carstenzimmermann.healthcard.entities;

import com.example.carstenzimmermann.healthcard.entities.Measurement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carsten Zimmermann on 02.01.2017.
 */

public class PercentilesRepository
{
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    public static List<PercentileValue> getPercentileData(int sex)
    {
        List<PercentileValue> percentileValueList = new ArrayList<PercentileValue>();
        PercentileValue percentileValue = null;

        switch (sex)
        {
            case MALE:
                percentileValueList.add(new PercentileValue(0, PercentileValue.WEEKS, 2.5f, 2.9f, 3.3f, 3.9f, 4.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(1, PercentileValue.WEEKS, 2.6f, 3.0f, 3.5f, 4.0f, 4.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(2, PercentileValue.WEEKS, 2.8f, 3.2f, 3.8f, 4.3f, 4.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(3, PercentileValue.WEEKS, 3.1f, 3.5f, 4.1f, 4.7f, 5.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(4, PercentileValue.WEEKS, 3.4f, 3.8f, 4.4f, 5.0f, 5.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(5, PercentileValue.WEEKS, 3.6f, 4.1f, 4.7f, 5.3f, 5.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(6, PercentileValue.WEEKS, 3.8f, 4.3f, 4.9f, 5.6f, 6.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(7, PercentileValue.WEEKS, 4.1f, 4.5f, 5.2f, 5.9f, 6.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(8, PercentileValue.WEEKS, 4.3f, 4.7f, 5.4f, 6.2f, 6.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(9, PercentileValue.WEEKS, 4.4f, 4.9f, 5.6f, 6.4f, 7.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(10, PercentileValue.WEEKS, 4.6f, 5.1f, 5.8f, 6.6f, 7.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(11, PercentileValue.WEEKS, 4.8f, 5.3f, 6.0f, 6.8f, 7.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(12, PercentileValue.WEEKS, 4.9f, 5.5f, 6.2f, 7.0f, 7.7f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(13, PercentileValue.WEEKS, 5.1f, 5.6f, 6.4f, 7.2f, 7.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                return percentileValueList;
            case FEMALE:
                //todo: Correct the values from male to actual female values
                percentileValueList.add(new PercentileValue(0, PercentileValue.WEEKS, 2.5f, 2.9f, 3.3f, 3.9f, 4.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(1, PercentileValue.WEEKS, 2.6f, 3.0f, 3.5f, 4.0f, 4.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(2, PercentileValue.WEEKS, 2.8f, 3.2f, 3.8f, 4.3f, 4.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(3, PercentileValue.WEEKS, 3.1f, 3.5f, 4.1f, 4.7f, 5.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(4, PercentileValue.WEEKS, 3.4f, 3.8f, 4.4f, 5.0f, 5.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(5, PercentileValue.WEEKS, 3.6f, 4.1f, 4.7f, 5.3f, 5.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(6, PercentileValue.WEEKS, 3.8f, 4.3f, 4.9f, 5.6f, 6.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(7, PercentileValue.WEEKS, 4.1f, 4.5f, 5.2f, 5.9f, 6.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(8, PercentileValue.WEEKS, 4.3f, 4.7f, 5.4f, 6.2f, 6.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(9, PercentileValue.WEEKS, 4.4f, 4.9f, 5.6f, 6.4f, 7.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(10, PercentileValue.WEEKS, 4.6f, 5.1f, 5.8f, 6.6f, 7.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(11, PercentileValue.WEEKS, 4.8f, 5.3f, 6.0f, 6.8f, 7.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(12, PercentileValue.WEEKS, 4.9f, 5.5f, 6.2f, 7.0f, 7.7f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(13, PercentileValue.WEEKS, 5.1f, 5.6f, 6.4f, 7.2f, 7.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                return percentileValueList;

        }
        return percentileValueList;
    }
}
