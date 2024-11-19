package co.edu.uptc;

import co.edu.uptc.interfaces.UfoGameInterface;
import co.edu.uptc.models.UfoSocketClient;
import co.edu.uptc.presenters.UfoGamePresenter;
import co.edu.uptc.views.ufogamemainframe.UfoGameView;

public class Main {
    public static void main(String[] args) {
       UfoGameInterface.Model model = new UfoSocketClient();
       UfoGameInterface.View view = new UfoGameView();
       UfoGameInterface.Presenter presenter = new UfoGamePresenter();
       model.setPresenter(presenter);
       view.setPresenter(presenter);
       presenter.setModel(model);
       presenter.setView(view);
       view.begin();
    }
}