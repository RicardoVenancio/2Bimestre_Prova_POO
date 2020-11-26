package view.pedido;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.application.LauncherImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

import alerts.ShowAlert;
import dao.PedidoDAO;
import entity.Pedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerPedido extends Application implements Initializable{

    @FXML
    private TextField TXTNome;

    @FXML
    private TextField TXTQtde;

    @FXML
    private TextField TXTUn;

    @FXML
    private TextField TXTVendpedido;

    @FXML
    private TextField TXTRespedido;

    @FXML
    private Button BTNSalvar;

    @FXML
    private Button BTNEditar;

    @FXML
    private Button BTNDeletar;

    @FXML
    private Button BTNPesquisar;

    @FXML
    private TextField TXTBuscarID;

    @FXML
    private Label LabelLabel;

    @FXML
    private DatePicker TXTData;
    
    @FXML
    private Label Labelid;
    
    @FXML
    private TableView<Pedido> TablePedido;
    
    @FXML
    private TableColumn<Pedido, Integer> ColunaPedido;

    @FXML
    private TableColumn<Pedido, String> ColunaNome;

    @FXML
    private TableColumn<Pedido, Date> ColunaDataPedido;

    @FXML
    private TableColumn<Pedido, Integer> ColunaQtde;

    @FXML
    private TableColumn<Pedido, String> ColunaUn;

    @FXML
    private TableColumn<Pedido, String> ColunaVendPedido;

    @FXML
    private TableColumn<Pedido, String> ColunaRespPedido;

    @FXML
    private ComboBox<Pedido> TESTE;

    @FXML
    void BuscarID(ActionEvent event) {
    	
    	try {
			ObservableList y = new PedidoDAO().listaApartamentos();
			TESTE.setItems(y);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String idString = TXTBuscarID.getText();
    	Pedido pedido = null;
    	if(!idString.equals("")) {
    		try {
    			int id = Integer.valueOf(idString);
    			pedido = new PedidoDAO().findByID(id);
    		} catch (Exception e) {
    	
    		}
    		if(pedido != null) {
    			Labelid.setVisible(true);
    			LabelLabel.setVisible(true);
    			LabelLabel.setText(pedido.getIdPedido()+  "");
    			TXTNome.setText(pedido.getNome());
    			TXTQtde.setText(pedido.getQuantidade() + "");
    			TXTData.setValue(pedido.getDataPedido().toLocalDate());
    			TXTUn.setText(pedido.getUnidademedida());
    			TXTVendpedido.setText(pedido.getVendedorPedido());
    			TXTRespedido.setText(pedido.getResponsavelPedido());
    		}
    	}
    	TXTBuscarID.clear();
    }

    @FXML
    void DeletarPedido(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     	 alert.setTitle("ATENÇÃO");
   	 alert.setHeaderText("EXCLUSÃO DE DADOS");
   	 alert.setContentText("VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O USUÁRIO");
          Optional<ButtonType> result = alert.showAndWait();
          
           if (result.get() == ButtonType.OK){
               Pedido pedido = obtemDadosID();
               int qtde = new PedidoDAO().deletar(pedido.getIdPedido());
               limpaCampo();
               listarPedido();
               StartTable();
           }
    }

    @FXML
    void EditarPedido(ActionEvent event) {
    	Pedido pedido = obtemDadosID();
    	limpaCampo();
    	int qtde = new PedidoDAO().alterar(pedido);
    	listarPedido();
    	StartTable();
    }

	@FXML
    void SalvarPedido(ActionEvent event) throws IOException {
    	if(validaCampos()) {
    		Pedido pedido = obtemDados();
    		limpaCampo();
    		int qtde = new PedidoDAO().inserir(pedido);
    		listarPedido();
    		StartTable();
    		Pedido ped = new Pedido();
    		System.out.println(ped);
  
    	}else {
			new ShowAlert().validaAlert();
    	}
    }
    
    private void limpaCampo() {
    	TXTNome.clear();
    	TXTQtde.clear();
    	TXTData.setValue(null);
    	TXTUn.clear();
    	TXTVendpedido.clear();
    	TXTRespedido.clear();
    	
    	LabelLabel.setVisible(false);
    	LabelLabel.setText("");
    }
    
    private void listarPedido() {
//    	textAreaLista.clear();
//    	List<Pedido> listapedido = new PedidoDAO().listAll();
//    	listapedido.forEach(pedido -> {
//    	textAreaLista.appendText(pedido.toString() +"\n");
//    });
    }

    private Pedido obtemDados() {
    	Pedido ped = new Pedido();
    	ped.setNome(TXTNome.getText());
    	ped.setQuantidade(Integer.valueOf(TXTQtde.getText()));
    	ped.setDataPedido(java.sql.Date.valueOf(TXTData.getValue()));
    	ped.setUnidademedida(TXTUn.getText());
    	ped.setVendedorPedido(TXTVendpedido.getText());
    	ped.setResponsavelPedido(TXTRespedido.getText());
    	return ped;
    }

    private Pedido obtemDadosID() {
    	Pedido ped = new Pedido();
    	ped.setIdPedido(Integer.valueOf(LabelLabel.getText()));
    	ped.setNome(TXTNome.getText());
    	ped.setQuantidade(Integer.valueOf(TXTQtde.getText()));
    	ped.setDataPedido(java.sql.Date.valueOf(TXTData.getValue()));
    	ped.setUnidademedida(TXTUn.getText());
    	ped.setVendedorPedido(TXTVendpedido.getText());
    	ped.setResponsavelPedido(TXTRespedido.getText());
    	return ped;
	}
    
    public boolean validaCampos() {
		if (TXTNome.getText().isEmpty() | TXTQtde.getText().isEmpty() | TXTUn.getText().isEmpty()
				| TXTVendpedido.getText().isEmpty() | TXTRespedido.getText().isEmpty()) {
			return false;
		}
		return true;
	}
    
	// Listar cadastros na TableView
	public void StartTable() {
		List<Pedido> list = new PedidoDAO().listAll();
		ColunaPedido.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("idPedido"));
		ColunaNome.setCellValueFactory(new PropertyValueFactory<Pedido, String>("nome"));
		ColunaDataPedido.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("dataPedido"));
		ColunaQtde.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("quantidade"));
		ColunaUn.setCellValueFactory(new PropertyValueFactory<Pedido, String>("unidademedida"));
		ColunaVendPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("vendedorPedido"));
		ColunaRespPedido.setCellValueFactory(new PropertyValueFactory<Pedido, String>("responsavelPedido"));
		TablePedido.setItems(atualizaTabela());
	}
	
	// Converter para Collections
	public ObservableList<Pedido> atualizaTabela() {
		PedidoDAO dao = new PedidoDAO();
		return FXCollections.observableArrayList(dao.listAll());
	}
    
//////////////////////////////////////////////////MÉTODO DE INICIALIZAÇÃO//////////////////////////////////////////////////////////
	public void execute() {
		launch();
	}

	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("PedidoFront.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	////////DELEETAR ESTE METODO E O COMBOBOX
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ObservableList y = new PedidoDAO().listaApartamentos();
			TESTE.setItems(y);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
