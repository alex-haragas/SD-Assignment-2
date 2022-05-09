package com.example.sa2.controller;

import com.example.sa2.Service.OrderService;
import com.example.sa2.model.Client;
import com.example.sa2.model.Food;
import com.example.sa2.model.Order;
import com.example.sa2.security.pwt.AuthTokenFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@RestController
@RequestMapping("/pdf")
public class pdfCreator {
    @Autowired
    OrderService orderService;

    private static final Logger logger = LogManager.getLogger(AuthTokenFilter.class);

    @PostMapping("/create")
    public ResponseEntity<String> createPDF(@RequestBody Order order){
       try{
           Document d=new Document();
           Client c=order.getClient();
           String name=c.getUsername()+order.getId()+".pdf";
           PdfWriter.getInstance(d, new FileOutputStream(name));
           d.open();
           Paragraph userData=new Paragraph();
           userData.add(c.getUsername()+'\n'+c.getAddress()+"\n\n");
           d.add(userData);
           float price=0;
           for(Food f:order.getFoods()){
               Paragraph foodPar=new Paragraph();
               foodPar.add(f.getName()+' '+f.getPrice());
               d.add(foodPar);
               price+=f.getPrice();
           }
           Paragraph finalPrice=new Paragraph();
           finalPrice.add("\n"+price);
           d.add(finalPrice);
           d.close();
           logger.info("Created new file");
           return new ResponseEntity<>("File Created",HttpStatus.CREATED);
       }
       catch (FileNotFoundException | DocumentException e){
           logger.error("Could not create file");
           return new ResponseEntity<>("File already Exist",HttpStatus.OK);
       }
    }
}
