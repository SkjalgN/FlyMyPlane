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
import com.mygdx.game.Model.Score;
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

public class GameController extends Game {
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
	private String playerName;

	private API Database;

	public GameController(API database) {
		Database = database;
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
		changeStateButton(this.startGameView.getStartGameButton(), new Callback() {
			@Override
			public void execute() {
				menuView();
			}
		});
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

	public void victoryView() {

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
				tutorialView.test();
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
		this.playerName = this.selectionView.getInputField().getText();
	}

	public void startInputField() {
		this.selectionView.getInputField().setTextFieldListener(
				new TextField.TextFieldListener() {
					@Override
					public void keyTyped(TextField textField, char c) {
						getUsername();
					}
				});
	}

	public void startGameView() {
		this.startGameView = new StartGameView(gsm, Database);
		gsm.push(this.startGameView);

		// Knappene som aktiveres skal hit
		startMenuViewButton();
	}

	public void menuView() {
		this.menuView = new MenuView(gsm, Database);
		gsm.set(this.menuView);

		// Knappene som aktiveres på MenuView
		startSelectionViewButton();
		startTutorialViewButton();
		startScoreboardViewButton();

	}

	public void selectionView() {
		this.selectionView = new SelectionView(gsm, Database);
		gsm.set(this.selectionView);

		// Knappene som aktiveres på SelectionView
		setPlaneSkinButton();
		startInputField();
		startGameStateButton();
	}

	public void tutorialView() {
		this.tutorialView = new TutorialView(gsm, Database);
		gsm.set(this.tutorialView);

		// Knappene som aktiveres på TutorialView
		exitTutorialViewButton();
		startTutorialViewNextButton();
	}

	public void scoreboardView() {
		this.scoreboardView = new ScoreboardView(gsm, Database);
		gsm.set(scoreboardView);

		// Knappene som aktiveres på scoreboardView
		startMenuViewScoreButton();
	}
	public void exitPauseView(){
		gsm.pop();

		startGameButtons();
	}
	public void menuFromPause(){
		gsm.pop();
		menuView();
	}

	public void gameView() {
		// if (gsm.getStateSize() > 1 && this.gameView != null) {
		// 	// FRA PAUSE TIL GAMEVIEW IGJEN
		// 	// LEGG TIL LOGIKK FOR TIDEN IGJEN!
		// 	gsm.pop();
		// } else {
			this.gameView = new GameView(gsm, Database, this.planeSkinVar);
			gsm.set(this.gameView);
		// }

		// Knappene som aktiveres på gameView
		startGameButtons();

	}

	public void pauseView() {
		this.pauseView = new PauseView(gsm, Database);
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
}