package com.atginfo;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        // Try block to attempt to connect to server
        try {
            // Get connection to server, { Address, username, password, port }
            Session session = FtpUtility.getSftpSession("ftp.myserver.com", "username", "password", 22);

            //Download file from server and save locally { session, server_filename, local_filename }
            FtpUtility.getSftpFile(session, "20151101_daily_amount_file.csv", "temp_local_file.csv");

        } catch (JSchException e) {
            // ERROR CONNECTING TO SERVER
            // TAKE SOME ACTION

        } catch (SftpException e) {
            // ERROR DOWNLOADING FILE
            // TAKE SOME ACTION
        }
    //// Read File
        try {
            BufferedReader fileReader = FileUtility.getFileReader("temp_local_file.csv");
            String line = null;
            while((line = fileReader.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    // Parse integer value from amount field
                    Double amount = Double.parseDouble(values[5]);

                    // Option 1 - Force Exception
                    if (amount < 0 ) {
                        // FORCE BUSINESS RULE
                        throw new AmountLessThanZeroBusinessRuleFailure();
                    }

                    //Option 2 - Take action within if block
                    if (amount < 0 ) {
                        // FORCE BUSINESS RULE
                        // Send email with line information
                        // Create log entry
                        // Do fun stuff!
                    }

                    // TODO
                    // Calculate new amount
                    //Add amount to Line
                    // Save file to server
///////////////////////   EXCEPTIONS  //////////////////////////////////////////////
                } catch (NumberFormatException e) {
                    //AMOUNT IS NOT AN DECIMAL NUMBER
                    //TAKE SOME ACTION
                } catch (AmountLessThanZeroBusinessRuleFailure amountLessThanZeroBusinessRuleFailure) {
                    // Send email with line information
                    // Create log entry
                    // Do fun stuff!
                }
            }
        } catch (FileNotFoundException e) {
            // FILE NOT FOUND
            // TAKE SOME ACTION

        } catch (IOException e) {
            // FILE COULD NOT BE READ
            // TAKE SOME ACTION
        }
    }
}
