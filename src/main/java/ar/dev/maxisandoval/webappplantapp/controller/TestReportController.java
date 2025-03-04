package ar.dev.maxisandoval.webappplantapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

@Controller
public class TestReportController {

    private static final String REPORTS_DIR = "target/surefire-reports";

    @GetMapping("/test-report")
    public String showTestReport(Model model) {
        model.addAttribute("report", generateReport());
        return "test-report";
    }

    private String generateReport() {
        try (Stream<Path> paths = Files.list(Path.of(REPORTS_DIR))) {
            List<Path> txtFiles = paths.filter(path -> path.toString().endsWith(".txt")).toList();

            return txtFiles.isEmpty() ? "No test report files found." :
                    txtFiles.stream()
                            .map(this::readFileContent)
                            .collect(Collectors.joining("<hr/>"));
        } catch (IOException e) {
            return "Error reading test reports: " + e.getMessage();
        }
    }

    private String readFileContent(Path file) {
        try {
            String content = String.join("\n", Files.readAllLines(file));
            return "<h2>Archivo: " + file.getFileName() + "</h2><pre>" + content + "</pre>";
        } catch (IOException e) {
            return "<h2>Archivo: " + file.getFileName() + "</h2><pre>Error reading file.</pre>";
        }
    }
}