package br.com.heuwald.tetris.sound;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class PlayMidi {

	public PlayMidi() {
		new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				while (true) {
					
					Sequencer midi = null;
					try {
						midi = MidiSystem.getSequencer();
					} catch (MidiUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						continue;
					}
					
					try {
						

						midi.open();
//						midi.setSequence(MidiSystem.getSequence(getClass().getResourceAsStream("/br/com/heuwald/resources/musics/" + EnmMusicas.values()[((int) (Math.random() * EnmMusicas.values().length - 1))].getNome())));
						
						midi.setSequence(MidiSystem.getSequence(getClass().getResourceAsStream("/br/com/heuwald/resources/musics/" + EnmMusicas.KATIUSHA.getNome())));
						midi.start();

						while (true) {
							try {
								wait(500);
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (!midi.isRunning()) {
								try {
									midi.stop();
									midi.close();
								} catch (Exception e) {
									e.printStackTrace();
								}

								continue;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							midi.stop();
							midi.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					
					try {
						wait(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	public static void main(String[] args) {
		new PlayMidi();
	}
}
