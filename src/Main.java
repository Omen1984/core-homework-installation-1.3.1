import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        StringBuilder tempLog = new StringBuilder();
        File gameDir = new File("E://JavaProject/Games");

        gameDir.mkdir();
        File srcDir = createEssence(gameDir, "src", tempLog);
        File resDir = createEssence(gameDir, "res", tempLog);
        createEssence(gameDir, "savegames", tempLog);
        File tempDir = createEssence(gameDir, "temp", tempLog);
        File mainDir = createEssence(srcDir, "main", tempLog);
        createEssence(srcDir, "test", tempLog);
        createEssence(mainDir, "Main.java", tempLog);
        createEssence(mainDir, "Utils.java", tempLog);
        createEssence(resDir, "drawables", tempLog);
        createEssence(resDir, "vectors", tempLog);
        createEssence(resDir, "icons", tempLog);
        File tempFile = createEssence(tempDir, "temp.txt", tempLog);
        writingTextToFile(tempFile, tempLog);
    }

    private static String fileOrDir(File file) {
        if (file.isDirectory()) {
            return "Директория: ";
        } else {
            return "Файл: ";
        }
    }

    private static void writingTextToFile(File file, StringBuilder sb) {
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write(sb.toString());
            fw.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    private static File createEssence(File dir, String essenceName, StringBuilder temp) {
        File essence = new File(dir, essenceName);
        if (essenceName.endsWith(".txt") || essenceName.endsWith(".java")) {
            try {
                if (essence.createNewFile()) {
                    temp.append(fileOrDir(essence) + "" + essenceName + " - Установлен\n");
                } else {
                    temp.append(fileOrDir(essence) + "" + essenceName + " - Не установлен(уже существует)\n");
                }
            } catch (IOException ex) {
                temp.append(essenceName + " - Не установлен" + ex.getMessage() + "\n");
            }
        } else {
            if (essence.mkdir()) {
                temp.append(fileOrDir(essence) + "" + essenceName + " - Установлен\n");
            } else {
                temp.append(fileOrDir(essence) + "" + essenceName + " - Не установлен(уже существует)\n");
            }
        }
        return essence;
    }
}
