package plus.knowing.chart;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

public class XYMain {

    public static void main(String[] args) throws IOException {
        savePng(createChart());
    }

    private static JFreeChart createChart() {
        Wrapper wrapper = generateData();
        CategoryPlot plot = new CategoryPlot();
        // X轴
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setCategoryMargin(0.0);
//        CombinedDomainCategoryPlot combinedDomainCategoryPlot = new CombinedDomainCategoryPlot(categoryAxis);
        // 折线
//        NumberAxis dateAxis = new NumberAxis();
//        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
//        CategoryPlot categoryPlot = new CategoryPlot(itemLine(wrapper.getLogItemList()), null, dateAxis, renderer);
//        combinedDomainCategoryPlot.add(categoryPlot);
        plot.setDomainAxis(categoryAxis);
        plot.setDataset(itemLine(wrapper.getLogItemList()));
        plot.setRenderer(new LineAndShapeRenderer());
        plot.setRangeAxis(new NumberAxis());
        // 均值
//        NumberAxis numberAxis = new NumberAxis();
//        LineAndShapeRenderer barRenderer = new LineAndShapeRenderer();
//        CategoryPlot categoryPlot1 =
//                new CategoryPlot(avgLine(wrapper.getLogItemList(), wrapper.getAvg()), null, numberAxis, barRenderer);
//        combinedDomainCategoryPlot.add(categoryPlot1);
        plot.setDataset(1, avgLine(wrapper.getLogItemList(), wrapper.getAvg()));
        plot.setRenderer(1, new LineAndShapeRenderer());
        plot.setRangeAxis(1, new NumberAxis());
        // 区域
        int index = 2;
        List<CategoryDataset> list = area(wrapper.getLogItemList(), wrapper.getItemList());
        for (CategoryDataset categoryDataset : list) {
//            NumberAxis areaAxis = new NumberAxis();
//            CategoryPlot areaPlot =
//                    new CategoryPlot(categoryDataset, null, areaAxis, new AreaRenderer());
            plot.setDataset(index, categoryDataset);
            plot.setRenderer(index, new AreaRenderer());
            plot.setRangeAxis(index, new NumberAxis());
            index++;
//            combinedDomainCategoryPlot.add(areaPlot);
        }
        // 点
//        NumberAxis pointAxis = new NumberAxis();
//        XYItemRenderer xyItemRenderer = new XYLineAndShapeRenderer(false, true);
//        XYPlot pointPlot =
//                new XYPlot(scatter(wrapper.getLogItemList(), wrapper.getItemList()), null, pointAxis, xyItemRenderer);
//        combinedDomainCategoryPlot.add(pointPlot);


        JFreeChart chart = new JFreeChart(wrapper.getParameterName(), null, plot, false);
        return chart;
    }

    private static void savePng(JFreeChart chart) throws IOException {
        File file = new File("E:/Video/test.png");
        ChartUtils.saveChartAsPNG(file, chart, 600, 400);
    }

    private static Wrapper generateData() {
        Wrapper wrapper = new Wrapper();
        wrapper.setAvg(72.22);
        wrapper.setParameterName("一次水温");
        wrapper.setParameterUnit("%");
        List<LogItem> logItemList = new ArrayList<>();
        logItemList.add(new LogItem(70.0, new Date(2022, 5, 1, 20, 20, 20)));
        logItemList.add(new LogItem(73.4, new Date(2022, 5, 2, 20, 20, 20)));
        logItemList.add(new LogItem(75.2, new Date(2022, 5, 3, 20, 20, 20)));
        logItemList.add(new LogItem(71.13, new Date(2022, 5, 4, 20, 20, 20)));
        logItemList.add(new LogItem(73.33, new Date(2022, 5, 5, 20, 20, 20)));
        logItemList.add(new LogItem(75.6, new Date(2022, 5, 6, 20, 20, 20)));
        logItemList.add(new LogItem(74.45, new Date(2022, 5, 7, 20, 20, 20)));
        wrapper.setLogItemList(logItemList);
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("PARAM", new Date(2022, 5, 2, 20, 20, 20), null, null));
        itemList.add(new Item("PARAM", new Date(2022, 5, 7, 20, 20, 20), null, null));
        itemList.add(new Item("VAR", null, new Date(2022, 5, 1, 20, 20, 20), new Date(2022, 5, 3, 20, 20, 20)));
        itemList.add(new Item("VAR", null, new Date(2022, 5, 3, 20, 20, 20), new Date(2022, 5, 4, 20, 20, 20)));
        wrapper.setItemList(itemList);
        return wrapper;
    }

    private static CategoryDataset itemLine(List<LogItem> logItemList) {
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        for (LogItem logItem : logItemList) {
            categoryDataset.addValue(logItem.value, "", logItem.getOperateDatetime());
        }
        return categoryDataset;
    }

    private static XYDataset avgLine(List<LogItem> logItemList, double avg) {
        DefaultXYDataset xyDataset = new DefaultXYDataset();
        for (LogItem logItem : logItemList) {
            xyDataset.addSeries(avg, new double[avg][]);
            xyDataset.addValue(avg, "", logItem.getOperateDatetime());
        }
        return xyDataset;
    }

    private static List<CategoryDataset> area(List<LogItem> logItemList, List<Item> itemList) {
        List<CategoryDataset> list = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getCompareValue().equals("VAR")) {
                DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
                for (LogItem logItem : logItemList) {
                    if (item.getStartDatetime().getTime() <= logItem.getOperateDatetime().getTime()
                        && item.getEndDatetime().getTime() >= logItem.getOperateDatetime().getTime()) {
                        categoryDataset.addValue(logItem.getValue(), "", logItem.getOperateDatetime());
                    } else {
                        categoryDataset.addValue(null, "", logItem.getOperateDatetime());
                    }
                }
                list.add(categoryDataset);
            }
        }

        return list;
    }

    private static XYDataset scatter(List<LogItem> logItemList, List<Item> itemList) {
        XYSeriesCollection dateset = new XYSeriesCollection();
//        int index = 0;
//        for (Item item : itemList) {
//            if (item.getCompareValue().equals("PARAM")) {
//                while (item.getOperateDatetime().getTime() < logItemList.get(index).getOperateDatetime().getTime()) {
//                    index++;
//                }
//                dateset.addValue(logItemList.get(index).getValue(), "", logItemList.get(index).getOperateDatetime());
//            }
//        }

        return dateset;
    }

    @Data
    static class Wrapper {

        private List<LogItem> logItemList;

        private List<Item> itemList;

        private Double avg;

        private String parameterUnit;

        private String parameterName;

    }

    @Data
    @AllArgsConstructor
    static class LogItem {
        private Double value;
        private Date operateDatetime;
    }

    @Data
    @AllArgsConstructor
    static class Item {
        private String compareValue;
        private Date operateDatetime;
        private Date startDatetime;
        private Date endDatetime;
    }

}
