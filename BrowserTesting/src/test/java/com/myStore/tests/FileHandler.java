package com.myStore.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileHandler {


    public static void makeFolder(String destinationPath) {
        File dir = new File(destinationPath);
        dir.mkdir();
    }

    public static String copyFileToNewFolder(String sourceFileName,
                                             String sourcePath, String destinationPath) {
        copyFileToNewFolderWithDifferentName(sourceFileName, sourcePath,
                sourceFileName, destinationPath);
        return sourceFileName;
    }

    public static boolean copyFileToNewFolderWithDifferentName(
            String sourceFileName, String sourcePath,
            String destinationFileName, String destinationPath) {
        File sourceFile = new File(sourcePath + sourceFileName);
        try {
            Files.copy(sourceFile.toPath(), (new File(destinationPath
                            + destinationFileName)).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean copyFolderToNewFolder(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        try {
            Files.copy(sourceFile.toPath(),
                    (new File(destinationPath)).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean copyFolderContentToNewFolder(String sourcePath, String destinationPath) {
        File source = new File(sourcePath);
        File destination = new File(destinationPath);
        try {
            FileUtils.copyDirectory(source, destination);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteFiles(String folder) {
        File index = new File(folder);
        String[] entries = index.list();
        if (entries == null) {
            return;
        }
        for (String s : entries) {
            File currentFile = new File(index.getPath(), s);
            currentFile.delete();
        }
    }

    public static boolean fileExists(String fileName) {
        File f = new File(fileName);
        if (f.exists() && !f.isDirectory())
            return true;
        return false;
    }

    public static String readFile(String pathname) throws IOException {
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    public static void saveBytesToFile(String filePath, byte[] fileBytes, boolean twice) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        try {
            String str = new String(fileBytes, UTF_8);
            str = StringUtils.newStringUtf8(Base64.decodeBase64(str));
            if (twice) {
                str = StringUtils.newStringUtf8(Base64.decodeBase64(str));
            }
            writer.write(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            writer.close();
        }
    }

    public static void saveStringToFile(String filePath, String writeString) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        try {
            writer.write(writeString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            writer.close();
        }
    }

    public static boolean createNewFile(String filePath) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}

