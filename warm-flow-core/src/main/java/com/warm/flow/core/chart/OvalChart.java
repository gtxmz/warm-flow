/*
 *    Copyright 2024-2025, Warm-Flow (290631660@qq.com).
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.warm.flow.core.chart;

import com.warm.flow.core.utils.DrawUtils;
import com.warm.flow.core.utils.ObjectUtil;
import com.warm.flow.core.utils.StringUtils;

import java.awt.*;

/**
 * 流程图开始或者结束节点
 */
public class OvalChart implements FlowChart {
    private int xStartOval;

    private int yStartOval;

    private Color c;

    private TextChart textChart;

    public OvalChart() {
    }

    public OvalChart(int xStartOval, int yStartOval, Color c, TextChart textChart) {
        this.xStartOval = xStartOval;
        this.yStartOval = yStartOval;
        this.c = c;
        this.textChart = textChart;
    }

    public int getxStartOval() {
        return xStartOval;
    }

    public OvalChart setxStartOval(int xStartOval) {
        this.xStartOval = xStartOval;
        return this;
    }

    public int getyStartOval() {
        return yStartOval;
    }

    public OvalChart setyStartOval(int yStartOval) {
        this.yStartOval = yStartOval;
        return this;
    }

    public Color getC() {
        return c;
    }

    public OvalChart setC(Color c) {
        this.c = c;
        return this;
    }

    public TextChart getTextChart() {
        return textChart;
    }

    public OvalChart setTextChart(TextChart textChart) {
        this.textChart = textChart;
        return this;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(c);
        graphics.drawOval(xStartOval - 20, yStartOval - 20, 40, 40);
        if (ObjectUtil.isNotNull(textChart) && StringUtils.isNotEmpty(textChart.getTitle())) {
            textChart.setxText(textChart.getxText() - DrawUtils.stringWidth(graphics, textChart.getTitle()) / 2);
            textChart.setyText(textChart.getyText() + 5);
            // 填充文字说明
            textChart.draw(graphics);
        }
    }
}
