package net.bethydiakabana.music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * MozartWaltzes.java simulates Wolfgang Amadeus Mozart's Musikalisches
 * Würfelspiel. At every execution, a new pseudorandomly generated piece of
 * music is composed and performed
 * <p>
 * In Mozart's game a two-part waltz (minuet and trio) is composed by joining
 * together randomly selected, pre-composed measures.
 * <p>
 * The minuet is 16 measures long, having 176 possible pre-composed measures.
 * The following table determines which pre-composed measures to play. Roll two
 * dice for each of the 16 measures. The sum of the dice rolls is on the
 * vertical axis; the measure of the minuet is on the horizontal axis.
 * <p>
 * The trio is also 16 measures long, with 96 possible pre-composed measures. To
 * determine which one to play, roll one die.
 * <p>
 * Composing a minuet and trio in this manner allows for 11^16 * 6^16 = 1.3 *
 * 10^29 different waltzes
 * 
 * @author Bethy Diakabana
 *
 */
public class MozartWaltzes {
	private final int[][] minuet = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 96, 22, 141, 41, 105, 122, 11, 30, 70, 121, 26, 9, 112, 49, 109, 14 },
			{ 0, 32, 6, 128, 63, 146, 46, 134, 81, 117, 39, 126, 56, 174, 18, 116, 83 },
			{ 0, 69, 95, 158, 13, 153, 55, 110, 24, 66, 139, 15, 132, 73, 58, 145, 79 },
			{ 0, 40, 17, 113, 85, 161, 2, 159, 100, 90, 176, 7, 34, 67, 160, 52, 170 },
			{ 0, 148, 74, 163, 45, 80, 97, 36, 107, 25, 143, 64, 125, 76, 136, 1, 93 },
			{ 0, 104, 157, 27, 167, 154, 68, 118, 91, 138, 71, 150, 29, 101, 162, 23, 151 },
			{ 0, 152, 60, 171, 53, 99, 133, 21, 127, 16, 155, 57, 175, 43, 168, 89, 172 },
			{ 0, 119, 84, 114, 50, 140, 86, 169, 94, 120, 88, 48, 166, 51, 115, 72, 111 },
			{ 0, 98, 142, 42, 156, 75, 129, 62, 123, 65, 77, 19, 82, 137, 38, 149, 8 },
			{ 0, 3, 87, 165, 61, 135, 47, 147, 33, 102, 4, 31, 164, 144, 59, 173, 78 },
			{ 0, 54, 130, 10, 103, 28, 37, 106, 5, 35, 20, 108, 92, 12, 124, 44, 131 } };

	private final int[][] trio = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 72, 6, 59, 25, 81, 41, 89, 13, 36, 5, 46, 79, 30, 95, 19, 66 },
			{ 0, 56, 82, 42, 74, 14, 7, 26, 71, 76, 20, 64, 84, 8, 35, 47, 88 },
			{ 0, 75, 39, 54, 1, 65, 43, 15, 80, 9, 34, 93, 48, 69, 58, 90, 21 },
			{ 0, 40, 73, 16, 68, 29, 55, 2, 61, 22, 67, 49, 77, 57, 87, 33, 10 },
			{ 0, 83, 3, 28, 53, 37, 17, 44, 70, 63, 85, 32, 96, 12, 23, 50, 91 },
			{ 0, 18, 45, 62, 38, 4, 27, 52, 94, 11, 92, 24, 86, 51, 60, 78, 31 } };

	private static final int DIE_FACES = 6;
	private static final int MEASURES = 16;
	private static final Random diceRoller = new Random();

	/**
	 * Composes a waltz with psudeorandomly selected pre-recorded measures
	 * 
	 * @return true if all measure files are played
	 */
	public boolean composeWaltz() {
		List<String> waltzMeasures = selectMeasures();
		List<File> audioFiles = new ArrayList<File>();
		Clip clip;
		boolean played = false;
		for (String measures : waltzMeasures)
			audioFiles.add(new File("src/resources/" + measures + ".wav"));
		for (File audioFile : audioFiles) {
			try {
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(audioFile));
				clip.start();
				long sleeptime = (clip.getMicrosecondLength() / 1000) - 50; 
				Thread.sleep(sleeptime);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			} // end try-catch
			played = true;
		} // end for
		return played;
	} // end method composeWaltz

	public boolean saveWaltz(List<File> wavFiles, String fileDestination) throws IOException {
		AudioInputStream audioInputStream = null;
		List<AudioInputStream> audioInputStreamList = null;
		AudioFormat audioFormat = null;
		long frameLength = 0;
		long excessFrames = 60000;
		boolean isWritten = false;

		try {
			for (File file : wavFiles) {
				audioInputStream = AudioSystem.getAudioInputStream(file);

				if (audioFormat == null)
					audioFormat = audioInputStream.getFormat();
				if (audioInputStreamList == null)
					audioInputStreamList = new ArrayList<AudioInputStream>();
				audioInputStreamList.add(audioInputStream);

				if (frameLength == 0)
					frameLength = (audioInputStream.getFrameLength() - excessFrames);
				else {
					frameLength += (audioInputStream.getFrameLength() - excessFrames);
				}
				frameLength += excessFrames;
			} // end for
			AudioSystem
					.write(new AudioInputStream(new SequenceInputStream(Collections.enumeration(audioInputStreamList)),
							audioFormat, frameLength), Type.WAVE, new File(fileDestination));
			isWritten = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (audioInputStream != null)
				audioInputStream.close();
			if (audioInputStreamList != null)
				audioInputStream = null;
		} // end try-catch
		return isWritten;
	} // end method saveWaltz
	
	private List<String> selectMeasures() {
		List<String> waltzMeasures = new ArrayList<String>();
		for (int measure = 1; measure <= MEASURES; measure++) {
			int diceRoll = throwDice(2); 
			waltzMeasures.add("M" + minuet[diceRoll][measure]);
		} // end for
		for (int measure = 1; measure <= MEASURES; measure++) {
			int diceRoll = throwDice(1);
			waltzMeasures.add("T" + trio[diceRoll][measure]);
		} // end for
		return waltzMeasures;
	} // end method composeMinuet

	private int throwDice(int numberOfDice) {
		int rolls = 0;
		if (numberOfDice <= 0)
			return rolls;
		for (int i = 0; i < numberOfDice; i++)
			rolls += (diceRoller.nextInt(DIE_FACES) + 1);
		return rolls;
	} // end method rollDiceForTrio
} // enc class MozartWaltzes
