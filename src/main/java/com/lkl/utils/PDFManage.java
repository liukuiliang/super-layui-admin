package com.lkl.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

/**
 * @author liukuiliang
 * pdf转换
 */
public class PDFManage {
             public static HttpServletResponse Pdf(ArrayList<String> imageUrllist, String filename, HttpServletResponse response) {
                 Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
                 String mOutputPdfFileName = "D:\\" + filename + ".pdf"; // 临时目录
                           try {
                               PdfWriter.getInstance(doc, new FileOutputStream(mOutputPdfFileName));
                               doc.open();
                               doc.newPage();
                               for (int i = 0; i < imageUrllist.size(); i++) {
//                                  doc.add(new Paragraph("efangtec")); // 文件logo
                                   Image png1 = Image.getInstance(imageUrllist.get(i));
                                   float heigth = png1.getHeight();
                                   float width = png1.getWidth();
                                   int percent = getPercent2(heigth, width);
                                   png1.setAlignment(Image.MIDDLE);
                                   png1.scalePercent(percent + 3);// 表示是原来图像的比例;
                                   doc.add(png1);
                               }
                               doc.close();
                               File file = new File(mOutputPdfFileName);
                               if (!file.exists()) {
                                   file.deleteOnExit();
                                   return null;
                               }
                               // 以流的形式下载文件。
                               InputStream fis = new BufferedInputStream(new FileInputStream(file));
                               byte[] buffer = new byte[fis.available()];
                               fis.read(buffer);
                               fis.close();
                               response.reset();
                               // 设置response的Header
                               response.setContentType("application/x-msdownload;");
                               response.addHeader("Content-disposition", "attachment;filename=" + new String(file.getName().getBytes("GBK"),"ISO-8859-1"));
                               response.addHeader("Content-Length", "" + file.length());
                               OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                               toClient.write(buffer);
                               toClient.flush();
                               toClient.close();
                               file.delete();
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                 return response;
             }


                 public static int getPercent(float h, float w) {
                     int p = 0;
                     float p2 = 0.0f;
                     if (h > w) {
                             p2 = 297 / h * 100;
                         } else {
                             p2 = 210 / w * 100;
                         }
                     p = Math.round(p2);
                     return p;
                 }

                 public static int getPercent2(float h, float w) {
                     int p = 0;
                     float p2 = 0.0f;
                     p2 = 530 / w * 100;
                     p = Math.round(p2);
                     return p;
                 }
}
