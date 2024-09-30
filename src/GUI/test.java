package GUI;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook(); // Tạo workbook
        Sheet sheet = workbook.createSheet("Sheet1"); // Tạo sheet

        Row row = sheet.createRow(0); // Tạo hàng
        Cell cell = row.createCell(0); // Tạo ô
        cell.setCellValue("Hello, Excel!"); // Ghi giá trị vào ô

        try (FileOutputStream outputStream = new FileOutputStream("example.xlsx")) {
            workbook.write(outputStream); // Ghi workbook vào file
            System.out.println("File Excel đã được tạo thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // try {
            //     // workbook.close(); // Đóng workbook
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        }
    }
}
