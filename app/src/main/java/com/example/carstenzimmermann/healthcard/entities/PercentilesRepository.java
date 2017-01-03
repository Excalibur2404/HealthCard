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
                percentileValueList.add(new PercentileValue(0, PercentileValue.WEEKS, 2.5f, 2.9f, 3.3f, 3.9f, 4.3f, 46.3f, 47.9f, 49.9f, 51.8f, 53.4f, 11.3f, 12.2f, 13.4f, 14.8f, 16.1f));
                percentileValueList.add(new PercentileValue(1, PercentileValue.WEEKS, 2.6f, 3.0f, 3.5f, 4.0f, 4.5f, 47.5f, 49.1f, 51.1f, 53.1f, 54.7f, 11.0f, 12.0f, 13.3f, 14.7f, 15.9f));
                percentileValueList.add(new PercentileValue(2, PercentileValue.WEEKS, 2.8f, 3.2f, 3.8f, 4.3f, 4.9f, 48.8f, 50.4f, 52.3f, 54.3f, 55.9f, 11.3f, 12.3f, 13.6f, 15.0f, 16.2f));
                percentileValueList.add(new PercentileValue(3, PercentileValue.WEEKS, 3.1f, 3.5f, 4.1f, 4.7f, 5.2f, 49.8f, 51.4f, 53.4f, 55.4f, 57.0f, 11.9f, 12.9f, 14.2f, 15.6f, 16.8f));
                percentileValueList.add(new PercentileValue(4, PercentileValue.WEEKS, 3.4f, 3.8f, 4.4f, 5.0f, 5.6f, 50.7f, 52.4f, 54.4f, 56.4f, 58.0f, 12.4f, 13.4f, 14.8f, 16.2f, 17.4f));
                percentileValueList.add(new PercentileValue(5, PercentileValue.WEEKS, 3.6f, 4.1f, 4.7f, 5.3f, 5.9f, 51.7f, 53.3f, 55.3f, 57.4f, 59.0f, 12.8f, 13.9f, 15.2f, 16.7f, 18.0f));
                percentileValueList.add(new PercentileValue(6, PercentileValue.WEEKS, 3.8f, 4.3f, 4.9f, 5.6f, 6.3f, 52.5f, 54.2f, 56.2f, 58.3f, 59.9f, 13.2f, 14.2f, 15.6f, 17.1f, 18.4f));
                percentileValueList.add(new PercentileValue(7, PercentileValue.WEEKS, 4.1f, 4.5f, 5.2f, 5.9f, 6.5f, 53.4f, 55.0f, 57.1f, 59.1f, 60.8f, 13.5f, 14.5f, 15.9f, 17.4f, 18.7f));
                percentileValueList.add(new PercentileValue(8, PercentileValue.WEEKS, 4.3f, 4.7f, 5.4f, 6.2f, 6.8f, 54.1f, 55.8f, 57.9f, 60.0f, 61.6f, 13.7f, 14.8f, 16.2f, 17.7f, 19.0f));
                percentileValueList.add(new PercentileValue(9, PercentileValue.WEEKS, 4.4f, 4.9f, 5.6f, 6.4f, 7.1f, 54.9f, 56.6f, 58.7f, 60.7f, 62.4f, 13.9f, 15.0f, 16.4f, 17.9f, 19.3f));
                percentileValueList.add(new PercentileValue(10, PercentileValue.WEEKS, 4.6f, 5.1f, 5.8f, 6.6f, 7.3f, 55.6f, 57.3f, 59.4f, 61.5f, 63.2f, 14.1f, 15.1f, 16.5f, 18.1f, 19.4f));
                percentileValueList.add(new PercentileValue(11, PercentileValue.WEEKS, 4.8f, 5.3f, 6.0f, 6.8f, 7.5f, 56.3f, 58.0f, 60.1f, 62.2f, 63.9f, 14.2f, 15.3f, 16.7f, 18.2f, 19.6f));
                percentileValueList.add(new PercentileValue(12, PercentileValue.WEEKS, 4.9f, 5.5f, 6.2f, 7.0f, 7.7f, 56.9f, 58.7f, 60.8f, 62.9f, 64.6f, 14.3f, 15.4f, 16.8f, 18.4f, 19.7f));
                percentileValueList.add(new PercentileValue(13, PercentileValue.WEEKS, 5.1f, 5.6f, 6.4f, 7.2f, 7.9f, 57.6f, 59.3f, 61.4f, 63.5f, 65.2f, 14.4f, 15.5f, 16.9f, 18.4f, 19.8f));
                percentileValueList.add(new PercentileValue(4, PercentileValue.MONTHS, 5.6f, 6.2f, 7.0f, 7.9f, 8.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(5, PercentileValue.MONTHS, 6.1f, 6.7f, 7.5f, 8.4f, 9.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(6, PercentileValue.MONTHS, 6.4f, 7.1f, 7.9f, 8.9f, 9.7f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(7, PercentileValue.MONTHS, 6.7f, 7.4f, 8.3f, 9.3f, 10.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(8, PercentileValue.MONTHS, 7.0f, 7.7f, 8.6f, 9.6f, 10.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(9, PercentileValue.MONTHS, 7.2f, 7.9f, 8.9f, 10.0f, 10.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(10, PercentileValue.MONTHS, 7.5f, 8.2f, 9.2f, 10.3f, 11.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(11, PercentileValue.MONTHS, 7.7f, 8.4f, 9.4f, 10.5f, 11.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(12, PercentileValue.MONTHS, 7.8f, 8.6f, 9.6f, 10.8f, 11.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(13, PercentileValue.MONTHS, 8.0f, 8.8f, 9.9f, 11.1f, 12.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(14, PercentileValue.MONTHS, 8.2f, 9.0f, 10.1f, 11.3f, 12.4f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(15, PercentileValue.MONTHS, 8.4f, 9.2f, 10.3f, 11.6f, 12.7f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(16, PercentileValue.MONTHS, 8.5f, 9.4f, 10.5f, 11.8f, 12.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(17, PercentileValue.MONTHS, 8.7f, 9.6f, 10.7f, 12.0f, 13.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(18, PercentileValue.MONTHS, 8.9f, 9.7f, 10.9f, 12.3f, 13.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(19, PercentileValue.MONTHS, 9.0f, 9.9f, 11.1f, 12.5f, 13.7f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(20, PercentileValue.MONTHS, 9.2f, 10.1f, 11.3f, 12.7f, 14.0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(21, PercentileValue.MONTHS, 9.3f, 10.3f, 11.5f, 13.0f, 14.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(22, PercentileValue.MONTHS, 9.5f, 10.5f, 11.8f, 13.2f, 14.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(23, PercentileValue.MONTHS, 9.7f, 10.6f, 12.0f, 13.4f, 14.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(24, PercentileValue.MONTHS, 9.8f, 10.8f, 12.2f, 13.7f, 15.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(25, PercentileValue.MONTHS, 10.0f, 11.0f, 12.4f, 13.9f, 15.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(26, PercentileValue.MONTHS, 10.1f, 11.1f, 12.5f, 14.1f, 15.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(27, PercentileValue.MONTHS, 10.2f, 11.3f, 12.7f, 14.4f, 15.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(28, PercentileValue.MONTHS, 10.4f, 11.5f, 12.9f, 14.6f, 16.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(29, PercentileValue.MONTHS, 10.5f, 11.6f, 13.1f, 14.8f, 16.4f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(30, PercentileValue.MONTHS, 10.7f, 11.8f, 13.3f, 15.0f, 16.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(31, PercentileValue.MONTHS, 10.8f, 11.9f, 13.5f, 15.2f, 16.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(32, PercentileValue.MONTHS, 10.9f, 12.1f, 13.7f, 15.5f, 17.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(33, PercentileValue.MONTHS, 11.1f, 12.2f, 13.8f, 15.7f, 17.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(34, PercentileValue.MONTHS, 11.2f, 12.4f, 14.0f, 15.9f, 17.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(35, PercentileValue.MONTHS, 11.3f, 12.5f, 14.2f, 16.1f, 17.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(36, PercentileValue.MONTHS, 11.4f, 12.7f, 14.3f, 16.3f, 18.0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(37, PercentileValue.MONTHS, 11.6f, 12.8f, 14.5f, 16.5f, 18.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(38, PercentileValue.MONTHS, 11.7f, 12.9f, 14.7f, 16.7f, 18.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(39, PercentileValue.MONTHS, 11.8f, 13.1f, 14.8f, 16.9f, 18.7f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(40, PercentileValue.MONTHS, 11.9f, 13.2f, 15.0f, 17.1f, 19.0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(41, PercentileValue.MONTHS, 12.1f, 13.4f, 15.2f, 17.3f, 19.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(42, PercentileValue.MONTHS, 12.2f, 13.5f, 15.3f, 17.5f, 19.4f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(43, PercentileValue.MONTHS, 12.3f, 13.6f, 15.5f, 17.7f, 19.7f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(44, PercentileValue.MONTHS, 12.4f, 13.8f, 15.7f, 17.9f, 19.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(45, PercentileValue.MONTHS, 12.5f, 13.9f, 15.8f, 18.1f, 20.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(46, PercentileValue.MONTHS, 12.7f, 14.1f, 16.0f, 18.3f, 20.4f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(47, PercentileValue.MONTHS, 12.8f, 14.2f, 16.2f, 18.5f, 20.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(48, PercentileValue.MONTHS, 12.9f, 14.3f, 16.3f, 18.7f, 20.9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(49, PercentileValue.MONTHS, 13.0f, 14.5f, 16.5f, 18.9f, 21.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(50, PercentileValue.MONTHS, 13.1f, 14.6f, 16.7f, 19.1f, 21.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(51, PercentileValue.MONTHS, 13.3f, 14.7f, 16.8f, 19.3f, 21.6f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(52, PercentileValue.MONTHS, 13.4f, 14.9f, 17.0f, 19.5f, 21.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(53, PercentileValue.MONTHS, 13.5f, 15.0f, 17.2f, 19.7f, 22.1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(54, PercentileValue.MONTHS, 13.6f, 15.2f, 17.3f, 19.9f, 22.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(55, PercentileValue.MONTHS, 13.7f, 15.3f, 17.5f, 20.1f, 22.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(56, PercentileValue.MONTHS, 13.8f, 15.4f, 17.7f, 20.3f, 22.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(57, PercentileValue.MONTHS, 13.9f, 15.6f, 17.8f, 20.5f, 23.0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(58, PercentileValue.MONTHS, 14.1f, 15.7f, 18.0f, 20.7f, 23.3f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(59, PercentileValue.MONTHS, 14.2f, 15.8f, 18.2f, 20.9f, 23.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
                percentileValueList.add(new PercentileValue(60, PercentileValue.MONTHS, 14.3f, 16.0f, 18.3f, 21.1f, 23.8f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
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
