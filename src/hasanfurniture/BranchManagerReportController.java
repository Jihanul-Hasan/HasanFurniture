package hasanfurniture;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

public class BranchManagerReportController implements Initializable {

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private LineChart<String, Number> linechart;

    @FXML
    private TextField day1, day2, day3, day4, day5, day6, day7;

    @FXML
    private RadioButton week1;
    @FXML
    private RadioButton week2;
    @FXML
    private RadioButton week3;
    @FXML
    private RadioButton week4;
    @FXML
    private TextField pdfRenameFx;
    @FXML
    private TextArea TextFieldFX;

    private final XYChart.Series<String, Number> FirstWeek = new XYChart.Series<String, Number>();
    private final XYChart.Series<String, Number> SecondWeek = new XYChart.Series<String, Number>();
    ArrayList<String> revenueData = new ArrayList<>();
    int i = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combobox.getItems().add("January");
        combobox.getItems().add("February");
        combobox.getItems().add("March");
        combobox.getItems().add("April");
        combobox.getItems().add("May");
        combobox.getItems().add("June");
        combobox.getItems().add("July");
        combobox.getItems().add("August");
        combobox.getItems().add("September");
        combobox.getItems().add("October");
        combobox.getItems().add("November");
        combobox.getItems().add("December");
    }

    @FXML
    void addAlDataOfDays(ActionEvent event) {

        linechart.getData().clear();
        if (week1.isSelected()) {

            FirstWeek.getData().add(new XYChart.Data<String, Number>("Day1", parseInt(day1.getText())));
            FirstWeek.getData().add(new XYChart.Data<String, Number>("Day2", parseInt(day2.getText())));
            FirstWeek.getData().add(new XYChart.Data<String, Number>("Day3", parseInt(day3.getText())));
            FirstWeek.getData().add(new XYChart.Data<String, Number>("Day4", parseInt(day4.getText())));
            FirstWeek.getData().add(new XYChart.Data<String, Number>("Day5", parseInt(day5.getText())));
            FirstWeek.getData().add(new XYChart.Data<String, Number>("Day6", parseInt(day6.getText())));
            FirstWeek.getData().add(new XYChart.Data<String, Number>("Day7", parseInt(day7.getText())));
            FirstWeek.setName("1ST Week");
            week1.setSelected(false);
            week1.setStyle("-fx-text-fill: #52E931;");

            revenueData.add(day1.getText());
            revenueData.add(day2.getText());
            revenueData.add(day3.getText());
            revenueData.add(day4.getText());
            revenueData.add(day5.getText());
            revenueData.add(day6.getText());
            revenueData.add(day7.getText());

            day1.clear();
            day2.clear();
            day3.clear();
            day4.clear();
            day5.clear();
            day6.clear();
            day7.clear();

        }
        if (week2.isSelected()) {

            SecondWeek.getData().add(new XYChart.Data<String, Number>("Day1", parseInt(day1.getText())));
            SecondWeek.getData().add(new XYChart.Data<String, Number>("Day2", parseInt(day2.getText())));
            SecondWeek.getData().add(new XYChart.Data<String, Number>("Day3", parseInt(day3.getText())));
            SecondWeek.getData().add(new XYChart.Data<String, Number>("Day4", parseInt(day4.getText())));
            SecondWeek.getData().add(new XYChart.Data<String, Number>("Day5", parseInt(day5.getText())));
            SecondWeek.getData().add(new XYChart.Data<String, Number>("Day6", parseInt(day6.getText())));
            SecondWeek.getData().add(new XYChart.Data<String, Number>("Day7", parseInt(day7.getText())));
            SecondWeek.setName("2nd Week");
            week2.setSelected(false);
            week2.setStyle("-fx-text-fill: #52E931;");
            day1.clear();
            day2.clear();
            day3.clear();
            day4.clear();
            day5.clear();
            day6.clear();
            day7.clear();

        }
    }
    //---------------GENERATE CHART ON CLICK-------------------//

    @FXML
    void generatechart(ActionEvent event) {

        linechart.getData().addAll(FirstWeek, SecondWeek);

    }

    //************Creats PNG Image and Exports Pdf*************//
    @FXML
    void exportaspdf(ActionEvent event) throws IOException {
        Scene scene = new Scene(new Group(), 900, 400);
        ((Group) scene.getRoot()).getChildren().add(linechart);
        WritableImage image = scene.snapshot(null);
        File file = new File("report.png");
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        generatePdfButtonOnClick();
        showAlert("Pdf Generated Succesfully!");

    }

    //---------------GENERATE PDF ON CLICK------------------------------//
    private void generatePdfButtonOnClick() {
        try {
            PdfWriter pw = new PdfWriter(new FileOutputStream(pdfRenameFx.getText() + ".pdf"));
            PdfDocument pdf = new PdfDocument(pw);
            pdf.addNewPage();
            Document doc = new Document(pdf);
            doc.setLeftMargin(70);

            PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            //---------SPACE------------//
            String newline = "\n";
            Paragraph lineSpace = new Paragraph(newline);
            lineSpace.setHeight(8);
            //-------------------------//
            //-------REPORT HEADING--------//
            Text titleText = new Text("                 Report of Product Sold in a week :");
            titleText.setFontSize(25f);
            Paragraph pageTitle = new Paragraph(titleText);
            pageTitle.setBold();
            titleText.setFontColor(Color.RED);
            doc.add(pageTitle);
            doc.add(lineSpace);
            //-------------------------------------------//
            //-----------Report Chart Image------------//
            ImageData data = ImageDataFactory.create("report.png");
            Image image = new Image(data);
            image.setAutoScale(true);
            doc.add(image);
            //-------------------------------------------//
            //-----------Report Data Table--------------//
            float colWidthArr[] = {50f, 100f};
            Table pdfTable = new Table(colWidthArr);

            pdfTable.addCell(new Cell().setBackgroundColor(Color.LIGHT_GRAY).add("Days: "));
            pdfTable.addCell(new Cell().setBackgroundColor(Color.LIGHT_GRAY).add("Revenue :"));

            for (String revenue : revenueData) {

                String count = Integer.toString(i++);
                pdfTable.addCell(new Cell().add(count));
                pdfTable.addCell(new Cell().add(revenue));

            }
            doc.add(pdfTable);
            doc.add(lineSpace);

            //-----------------REPORT HEADING----------------//
            Text ReportitleText = new Text("Report Description:  :");
            ReportitleText.setFontSize(14f);
            Paragraph reportTitle = new Paragraph(ReportitleText);
            reportTitle.setBold();
            ReportitleText.setFontColor(Color.RED);
            doc.add(reportTitle);
            //--------------------------------------------------//
            //----------------REPORT TEXTS---------------------//
            Text reportText = new Text(TextFieldFX.getText());
            reportText.setFontSize(12f);
            reportText.setFont(font1);
            Paragraph report = new Paragraph(reportText);
            doc.add(report);
            doc.add(lineSpace);
            doc.add(lineSpace);
            doc.add(lineSpace);

            doc.close();
            pdfRenameFx.clear();
            TextFieldFX.clear();
        } catch (Exception e) {

        }
    }

    private void showAlert(String str) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        a.getButtonTypes().setAll(ok);
        a.setContentText(str);
        a.showAndWait();
    }
}
