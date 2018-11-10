import java.io.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.io.FileUtils;

public class Crypto {
    public static void main(String args[]){
        String image = args[0];
        String object = args[1];
        System.out.println(image + " " + object);
        byte[] imageProcessed = hideFile(image, object);
        saveFile("ProcessedImage.png", imageProcessed);
        System.out.println("Saved the new image.");
        byte[] secondImage = unhideFile(accessBytes("ProcessedImage.png"), addFlavouring());
        saveFile("file", secondImage);
    }

    private static byte[] hideFile(String img, String obj){
        byte[] image = accessBytes(img);
        byte[] object = accessBytes(obj);
        byte[] imageProcessed = null;
        byte[] flavouring = addFlavouring();
        try{
            imageProcessed = ArrayUtils.addAll(image, flavouring);
            imageProcessed = ArrayUtils.addAll(imageProcessed, object);
            System.out.println("Added the byte arrays.");
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return imageProcessed;
    }

    private static byte[] unhideFile(byte[] file, byte[] flavour) {
        String fileArray = new String(file);
        String flavourArray = new String(flavour);
        int flavouring = fileArray.indexOf(flavourArray);
        StringBuilder fileArraySB = new StringBuilder(fileArray);
        fileArraySB.delete(0, (flavouring + flavourArray.length()));
        byte[] replacementArray = (fileArraySB.toString()).getBytes();
        return replacementArray;
    }

    private static byte[] accessBytes(String obj){
        System.out.println("Accessing bytes...");
        String pathOut = "C:\\Testing\\" + obj;
        File object = new File(pathOut);
        System.out.println("File path: " + pathOut);
        int fileLength = Math.toIntExact(object.length());
        byte[] objectData = new byte[fileLength];
        try{
            FileInputStream data = new FileInputStream(pathOut);
            data.read(objectData);
            data.close();
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return objectData;

    }

    private static void saveFile(String name, byte[] data){
        try{
            FileUtils.writeByteArrayToFile(new File("C:\\Testing\\" + name), data);
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }

    private static byte[] addFlavouring(){
        String flavouring = " URGAYPWND";
        byte[] flavouringBytes = flavouring.getBytes();
        return flavouringBytes;
    }
}