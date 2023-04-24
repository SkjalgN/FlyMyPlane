package com.mygdx.game.Controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.API;
import com.mygdx.game.GameOverEvent;
import com.mygdx.game.GameOverListener;
import com.mygdx.game.Model.Score;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.View.MenuView;
import com.mygdx.game.View.PauseView;
import com.mygdx.game.View.ScoreboardView;
import com.mygdx.game.View.GameStateManager;
import com.mygdx.game.View.GameView;
import com.mygdx.game.View.SelectionView;
import com.mygdx.game.View.StartGameView;
import com.mygdx.game.View.TutorialView;
import com.mygdx.game.View.VictoryView;

import java.util.ArrayList;

public class GameController extends Game implements GameOverListener {

	// This interface is used for methods to send in a method as a parameter
	public interface Callback {
		void execute();
	}

	private GameStateManager gsm;
	private SpriteBatch batch;

	// ALL THE VIEWS ARE CREATED HERE
	private StartGameView startGameView;
	private MenuView menuView;
	private SelectionView selectionView;
	private TutorialView tutorialView;
	private ScoreboardView scoreboardView;
	private VictoryView victoryView;
	private GameView gameView;
	private PauseView pauseView;

	// GameView variables
	private int planeSkinVar;
	private String playerName1;
	private String playerName2;
	private Score score1;
	private Score score2;
	private int elapsedTime;
	private Boolean nextPlayer = false;

	// for the music
	private AssetManager manager;

	private API Database;

	public GameController(API database) {
		this.Database = database;
	}

	public ArrayList<Score> listen;

	@Override
	public void create() {

		batch = new SpriteBatch();
		gsm = new GameStateManager();


		// THIS VIEW IS CREATED FIRST
		startGameView();
		/*
		 * gsm.push(this.StartGameView);
		 */
		/*
		 * Database.submitHighscore(new Score(3490,"Skjalg"));
		 * THIS METHOD IS USED TO SAVE SCORES TO DB IN THIS FORMAT!
		 * CREATE A NEW SCORE WITH SCORE FIRST, THEN USERNAME
		 */
		/* Database.submitHighscore(new Score(3490,"Skjalg")); */

	}

	// public SelectionView getSelectionView(){
	// return this.selectionView;
	// }
	// The button is activated at correct view
	public void startMenuViewButton() {
		changeStateButton(this.startGameView.getStartButton(), new Callback() {
			@Override
			public void execute() {
				menuView();
			}
		});
	}

	public void startMenuViewFromVictoryButton() {
		changeStateButton(this.victoryView.getNextButton(), new Callback() {
			@Override
			public void execute() {
				menuView();
			}
		});
	}

	public void startNextPlayerTurn() {
		if (nextPlayer) {
			selectionView();
		}
	}

	public void exitFromPause() {
		changeStateButton(this.pauseView.getExitButton(), new Callback() {
			@Override
			public void execute() {
				menuFromPause();
			}
		});
	}

	public void startMenuViewScoreButton() {
		changeStateButton(
				this.scoreboardView.getBackButton(), new Callback() {
					@Override
					public void execute() {
						menuView();
					}
				});
	}

	public void startPauseButtons() {
		changeStateButton(
				this.pauseView.getContinueButton(), new Callback() {
					@Override
					public void execute() {
						// Gå tilbake fra Pause til Gamestate igjen
						// gameView();
						exitPauseView();

					}
				});
		changeStateButton(
				this.pauseView.getSoundButton(), new Callback() {
					@Override
					public void execute() {
						// Metoden din for
						// changeTexture();

						// muteSound();
						if (manager.get("Audio/background.ogg", Music.class).isPlaying()) {
							manager.get("Audio/background.ogg", Music.class).pause();
							pauseView.soundOffButton();
						} else {
							manager.get("Audio/background.ogg", Music.class).play();
							pauseView.soundOnButton();
						}
					}
				});
	}

	public void startGameButtons() {
		changeStateButton(
				this.gameView.getPauseButton(), new Callback() {
					@Override
					public void execute() {
						pauseView();
					}
				});
		changePlaneStateGameButtons(this.gameView.getLeftButton(), new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().rotateLeft();
			}
		}, new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().stopRotateLeft();
			}
		});
		changePlaneStateGameButtons(this.gameView.getRightButton(), new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().rotateRight();
			}
		}, new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().stopRotateRight();
			}
		});
		changePlaneStateGameButtons(this.gameView.getBoostButton(), new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().setSpeed(15);
				gameView.getPlane().setAirflowvar(0);
			}
		}, new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().setSpeed(3);
				gameView.getPlane().setAirflowvar(1);
			}
		});
		changePlaneStateGameButtons(this.gameView.getFlameButton(), new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().setFlamevar(1);
			}
		}, new Callback() {
			@Override
			public void execute() {
				gameView.getPlane().setFlamevar(0);
			}
		});
	}

	public void startScoreboardViewButton() {
		changeStateButton(this.menuView.getScoreBoardButton(), new Callback() {
			@Override
			public void execute() {
				scoreboardView();
			}
		});
	}

	// The button is activated at correct view
	public void startSelectionViewButton() {
		changeStateButton(this.menuView.getSelectionButton(), new Callback() {
			@Override
			public void execute() {
				selectionView();
			}
		});
	}

	public void startTutorialViewButton() {
		changeStateButton(this.menuView.getTutorialButton(), new Callback() {
			@Override
			public void execute() {
				tutorialView();
			}
		});
	}

	public void exitTutorialViewButton() {
		changeStateButton(this.tutorialView.getTutorialExitButton(), new Callback() {
			@Override
			public void execute() {
				menuView();
			}
		});
	}

	public void startTutorialViewNextButton() {
		changeStateButton(this.tutorialView.getTutorialNextButton(), new Callback() {
			@Override
			public void execute() {
				tutorialView.nextPage();
			}
		});

	}

	public void startTutorialViewBackButton() {
		changeStateButton(this.tutorialView.getTutorialBackButton(), new Callback() {
			@Override
			public void execute() {
				tutorialView.backPage();
			}
		});

	}

	public void startGameStateButton() {
		changeStateButton(this.selectionView.getNextButton(), new Callback() {
			@Override
			public void execute() {
				// getSelectionView().setSelectionStateStatusLoading();
				// //Her skal switchen skje
				// getSelectionView().setSelectionStateStatusSwitching();
				gameView();
			}
		});
	}

	public void changePlaneStateGameButtons(Button button, final Callback callback1, final Callback callback2) {
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// plane.rotateRight();
				callback1.execute();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				// plane.stopRotateRight();
				callback2.execute();
			}
		});

	}

	// StartGameButton
	public void changeStateButton(Button button, final Callback callback) {
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				callback.execute();
				return true;
			}
		});
	}

	private void selectionViewChangeBox(int i) {
		this.selectionView.changeBox(i);
	}

	public void setPlaneSkinButton() {
		changeStateButton(this.selectionView.getBoxButton(1), new Callback() {
			@Override
			public void execute() {
				planeSkinVar = 0;
				selectionViewChangeBox(1);
			}
		});
		changeStateButton(this.selectionView.getBoxButton(2), new Callback() {
			@Override
			public void execute() {
				planeSkinVar = 1;
				selectionViewChangeBox(2);

			}
		});
		changeStateButton(this.selectionView.getBoxButton(3), new Callback() {
			@Override
			public void execute() {
				planeSkinVar = 2;
				selectionViewChangeBox(3);

			}
		});
		changeStateButton(this.selectionView.getBoxButton(4), new Callback() {
			@Override
			public void execute() {
				planeSkinVar = 3;
				selectionViewChangeBox(4);

			}
		});
		changeStateButton(this.selectionView.getBoxButton(5), new Callback() {
			@Override
			public void execute() {
				planeSkinVar = 4;
				selectionViewChangeBox(5);

			}
		});
		changeStateButton(this.selectionView.getBoxButton(6), new Callback() {
			@Override
			public void execute() {
				planeSkinVar = 5;
				selectionViewChangeBox(6);

			}
		});
	}

	public void getUsername() {
		if (!nextPlayer) {

			this.playerName1 = this.selectionView.getInputField().getText();
		} else {
			this.playerName2 = this.selectionView.getInputField().getText();

		}
	}

	public void startInputField() {
		this.selectionView.getInputField().setTextFieldListener(
				new TextField.TextFieldListener() {
					@Override
					public void keyTyped(TextField textField, char c) {
						getUsername();
						showPlanesAndButtons();
						setPlaneSkinButton();
					}
				});
	}

	private void showPlanesAndButtons(){
		this.selectionView.showPlanes();
	}

	public void startGameView() {
		manager = new AssetManager();
		manager.load("Audio/background.ogg",Music.class);
		manager.finishLoading();
		this.startGameView = new StartGameView(gsm);
		gsm.push(this.startGameView);
		manager.get("Audio/background.ogg", Music.class).setLooping(true);
		manager.get("Audio/background.ogg", Music.class).play();
		// Knappene som aktiveres skal hit
		startMenuViewButton();
	}

	public void menuView() {
		this.menuView = new MenuView(gsm);
		gsm.set(this.menuView);

		// Knappene som aktiveres på MenuView
		startSelectionViewButton();
		startTutorialViewButton();
		startScoreboardViewButton();

	}

	public void selectionView() {
		this.selectionView = new SelectionView(gsm, Database, this.nextPlayer);
		gsm.set(this.selectionView);

		// Knappene som aktiveres på SelectionView
		startInputField();
		startGameStateButton();
	}

	public void tutorialView() {
		this.tutorialView = new TutorialView(gsm);
		gsm.set(this.tutorialView);

		// Knappene som aktiveres på TutorialView
		exitTutorialViewButton();
		startTutorialViewNextButton();
		startTutorialViewBackButton();
	}

	public void scoreboardView() {
		this.scoreboardView = new ScoreboardView(gsm, Database);
		gsm.set(scoreboardView);

		// Knappene som aktiveres på scoreboardView
		startMenuViewScoreButton();
	}

	public void exitPauseView() {
		gsm.pop();

		startGameButtons();
		gameView.setInputProcessorManually();
	}

	public void menuFromPause() {
		gsm.pop();
		menuView();
	}

	public void gameView() {
		// if (gsm.getStateSize() > 1 && this.gameView != null) {
		// // FRA PAUSE TIL GAMEVIEW IGJEN
		// // LEGG TIL LOGIKK FOR TIDEN IGJEN!
		// gsm.pop();
		// } else {
		this.gameView = new GameView(gsm, Database, this.planeSkinVar);
		gsm.set(this.gameView);

		// Lager en gameover lytter i dette viewet
		gameView.addGameOverListener(this);
		// }

		// Knappene som aktiveres på gameView
		startGameButtons();
		gameView.setInputProcessorManually();

	}

	public void pauseView() {
		this.pauseView = new PauseView(gsm);
		gsm.push(pauseView);

		// HUSK Å PAUSE TIDEN SOM TELLES!

		// Knappene som aktiveres på pauseView
		startPauseButtons();
		exitFromPause();

	}
	//

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	public void handleInput() {

	}

	@Override
	public void onGameOver(GameOverEvent event) {
		// TODO Auto-generated method stub
		// AFTER GAME IS OVER!
		// Player 1
		if (!this.nextPlayer) {
			this.elapsedTime = this.gameView.getElapsedTime();
			System.out.println("Elapsed time: " + this.elapsedTime);
			this.score1 = new Score(this.elapsedTime, this.playerName1);

			// this.victoryView = new VictoryView(gsm, Database, this.score1);
			// Kanskje push?

			this.gameView.removeGameOverListener(this);
			this.nextPlayer = true;
			startNextPlayerTurn();

		} else if (this.nextPlayer) {
			this.elapsedTime = this.gameView.getElapsedTime();
			System.out.println("Elapsed time: " + this.elapsedTime);
			this.score2 = new Score(this.elapsedTime, this.playerName2);

			this.gameView.removeGameOverListener(this);

			this.nextPlayer = false;
			// Ta inn begge scores
			this.victoryView = new VictoryView(gsm, Database, this.score1, this.score2);

			// Knapp for menuview
			gsm.set(victoryView);
			startMenuViewFromVictoryButton();
			//Save the winner to database
			Score winner = victoryView.getWinner();
			this.Database.submitHighscore(new Score(winner.getScore(), winner.getName()));


		}

		// Denne skal vekk, ny selctionView

	}
}