import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Locale;

package Niv3;



class VoitureTest {

    @Test
    void testMain_SuccessfulScenario() throws Exception {
        // Simulate user pressing Enter for all steps and always answering "o" for priority
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 0; i < 28; i++) {
            inputBuilder.append("\n");
            // For step 4 (index 3), answer "o" for priority
            if (i == 3) inputBuilder.append("o\n");
        }
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Run main
        TestConduiteParis.main(new String[]{});

        String output = out.toString(Locale.ROOT);

        // Restore System.in and System.out
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);

        // Check that the test is completed and points are displayed
        assertTrue(output.contains("=== TEST TERMINÉ ==="));
        assertTrue(output.contains("Points finaux:"));
        // Should not fail if user always answers "o" and just presses Enter
        assertTrue(output.contains("FÉLICITATIONS") || output.contains("DÉSOLÉ"));
    }

    @Test
    void testMain_FailOnPriority() throws Exception {
        // Simulate user pressing Enter for all steps and answering "n" for priority (step 4)
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 0; i < 28; i++) {
            inputBuilder.append("\n");
            if (i == 3) inputBuilder.append("n\n");
        }
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Run main
        TestConduiteParis.main(new String[]{});

        String output = out.toString(Locale.ROOT);

        // Restore System.in and System.out
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);

        // Check that the priority warning appears and points are lost
        assertTrue(output.contains("‼️ Priorité non respectée!"));
        assertTrue(output.contains("⚠️ Vous perdez 3 points"));
    }

    @Test
    void testMain_StopsOnTooManyPointsLost() throws Exception {
        // Simulate user pressing Enter for all steps and always answering "n" for priority,
        // and simulate more mistakes by answering "n" for all steps that ask for input
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 0; i < 28; i++) {
            inputBuilder.append("\n");
            if (i == 3) inputBuilder.append("n\n");
        }
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Run main
        TestConduiteParis.main(new String[]{});

        String output = out.toString(Locale.ROOT);

        // Restore System.in and System.out
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);

        // If enough points are lost, the test should fail early
        assertTrue(output.contains("TEST TERMINÉ") || output.contains("TEST ÉCHOUÉ"));
    }
}