public class CodeCoverageExtraction {
    import org.jacoco.core.analysis.ICoverageNode;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.IMethodCoverage;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.tools.ExecFileLoader;
import org.jacoco.report.check.Rule;
import org.jacoco.report.check.RulesChecker;
import org.jacoco.report.xml.XMLFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

    public class CodeCoverageExtractor {
        public static void main(String[] args) throws IOException {
            File jacocoExecFile = new File("path/to/jacoco.exec");
            File jacocoReportFile = new File("path/to/jacoco.xml");

            // Load the execution data
            ExecFileLoader execFileLoader = new ExecFileLoader();
            execFileLoader.load(jacocoExecFile);

            // Load the XML formatter
            XMLFormatter xmlFormatter = new XMLFormatter();
            xmlFormatter.setOutput(new FileOutputStream(jacocoReportFile), false);

            // Create the coverage checker
            RulesChecker rulesChecker = new RulesChecker();
            rulesChecker.setExecutionDataStore(new ExecutionDataStore());
            rulesChecker.addRule(Rule.RULES);

            // Create a list of classes with their coverage data
            List<IClassCoverage> classes = execFileLoader.getExecutionDataStore().getContents();

            // Extract and print coverage metrics
            for (IClassCoverage coverage : classes) {
                System.out.println("Class: " + coverage.getName());
                System.out.println("Coverage: " + coverage.getLineCounter().getCoveredRatio() * 100 + "%");
            }

            // Generate an XML report
            xmlFormatter.begin();
            xmlFormatter.footer();
            xmlFormatter.close();
        }
    }

}
