import java.io.*;
class readFile{

	public static void main(String args[])
	{
		try (BufferedReader br = new BufferedReader(new FileReader("reviews.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

