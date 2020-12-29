package application;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import metier.MCategorie;
import metier.MClient;
import metier.MCommande;
import metier.MLigneCommande;
import metier.MProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		try {
			URL fxmlURL = getClass().getResource("/vue/sqlOuListe.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((AnchorPane) root, 640, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Fenetre de gestion des pulls moches");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}

		/*Scanner scanner = new Scanner(System.in);
		System.out.println("Passer de MySQL a ListeMemoire?  1:yes 2:no");
		int option = scanner.nextInt();

		// le launch(args) permet de lancer l'application, à placer en bon endroit en fonction de la ou on veut aller et de quelle page on veut ouvrir
		if (option == 1) {
			DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
			System.out.println("choisir une partie");
			System.out.println("1: Categorie 2: Produit 3: Client 4: Commande 5: Ligne de commande");
			int partie = scanner.nextInt();
			// categorie
			if (partie == 1) {
				System.out.println("Cat�gorie");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("Id categorie=");
					int idc = scanner.nextInt();
					System.out.println("titre=");
					String titre = scanner.next();
					System.out.println("visuel=");
					String visuel = scanner.next();
					try {
						daos.getCategorieDAO().create(new MCategorie(idc, titre, visuel));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id_categorie=");
					int id = scanner.nextInt();
					System.out.println("titre=");
					String titre = scanner.next();
					System.out.println("visuel=");
					String visuel = scanner.next();
					try {
						daos.getCategorieDAO().update(new MCategorie(id, titre, visuel));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id_categorie=");
					int id = scanner.nextInt();
					try {
						daos.getCategorieDAO().delete(new MCategorie(id));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_categorie=");
					int id = scanner.nextInt();
					try {
						daos.getCategorieDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("cette categorie n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}
			}


// Produit
			else if (partie == 2) {
				System.out.println("Produit");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("Id_produit");
					int idp = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("description=");
					String description = scanner.next();
					System.out.println("tarif=");
					float tarif = scanner.nextFloat();
					System.out.println("visuel=");
					String visuel = scanner.next();
					System.out.println("id cat�gorie=");
					int idc = scanner.nextInt();
					try {
						daos.getProduitDAO().create(new MProduit(idp, nom, description, tarif, visuel, idc));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id produit");
					int idp = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("description=");
					String description = scanner.next();
					System.out.println("tarif=");
					float tarif = scanner.nextFloat();
					System.out.println("visuel=");
					String visuel = scanner.next();
					System.out.println("id cat�gorie=");
					int idc = scanner.nextInt();
					try {
						daos.getProduitDAO().update(new MProduit(idp, nom, description, tarif, visuel, idc));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id produit=");
					scanner.nextInt();
					try {
						daos.getProduitDAO().delete(new MProduit(scanner));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_produit=");
					int id = scanner.nextInt();
					try {
						daos.getProduitDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("ce produit n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}


			}

// Client
			else if (partie == 3) {
				System.out.println("Client");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("prenom=");
					String prenom = scanner.next();
					System.out.println("identifiant=");
					String iden = scanner.next();
					System.out.println("mode de passe =");
					String mdp = scanner.next();
					System.out.println("adr_numero=");
					int adr = scanner.nextInt();
					System.out.println("adr_voie=");
					String adr_voie = scanner.next();
					System.out.println("adr_code_postal=");
					int adr_code_postal = scanner.nextInt();
					System.out.println("adr_ville=");
					String adr_ville = scanner.next();
					System.out.println("adr_pays=");
					String adr_pays = scanner.next();
					try {
						daos.getClientDAO().create(new MClient(id, nom, prenom, iden, mdp, adr, adr_voie, adr_code_postal, adr_ville, adr_pays));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("prenom=");
					String prenom = scanner.next();
					System.out.println("identifiant=");
					String iden = scanner.next();
					System.out.println("mode de passe =");
					String mdp = scanner.next();
					System.out.println("adr_numero=");
					int adr = scanner.nextInt();
					System.out.println("adr_voie=");
					String adr_voie = scanner.next();
					System.out.println("adr_code_postal=");
					int adr_code_postal = scanner.nextInt();
					System.out.println("adr_ville=");
					String adr_ville = scanner.next();
					System.out.println("adr_pays=");
					String adr_pays = scanner.next();
					try {
						daos.getClientDAO().update(new MClient(id, nom, prenom, iden, mdp, adr, adr_voie, adr_code_postal, adr_ville, adr_pays));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					try {
						daos.getClientDAO().delete(new MClient(id));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					try {
						daos.getClientDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("ce client n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}
			}


//commande
			else if (partie == 4) {
				System.out.println("Commande");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("date_commande=");
					String date = scanner.next();
					DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate dateDebut = LocalDate.parse(date, formatage);
					System.out.println("id_client");
					int idcl = scanner.nextInt();
					try {
						daos.getCommandeDAOO().create(new MCommande(dateDebut, idcl));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					System.out.println("date_commande=");
					String date = scanner.next();
					DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate dateDebut = LocalDate.parse(date, formatage);
					System.out.println("id_client");
					int idcl = scanner.nextInt();
					try {
						daos.getCommandeDAOO().update(new MCommande(id, dateDebut, idcl));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					try {
						daos.getCommandeDAOO().delete(new MCommande(id));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					try {
						daos.getCommandeDAOO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("cette commande n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}

			}
//ligne de commande
			else if (partie == 5) {
				System.out.println("Ligne de commande");
				System.out.println("Choisir une m�thode");
				System.out.println("1: Ajouter 2: Modifier 3: Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("Id_commande = ");
					int id_commande = scanner.nextInt();
					System.out.println("id_produit = ");
					int id_produit = scanner.nextInt();
					System.out.println("quantite = ");
					int quantite = scanner.nextInt();
					System.out.println("tarif_unitaire=");
					float tarif_unitaire = scanner.nextFloat();

					try {
						daos.getLigneCommandeDAO().create(new MLigneCommande(id_commande, id_produit, quantite, tarif_unitaire));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("Ajouter");
					System.out.println("Id_commande = ");
					int id_commande = scanner.nextInt();
					System.out.println("id_produit = ");
					int id_produit = scanner.nextInt();
					System.out.println("quantite = ");
					int quantite = scanner.nextInt();
					System.out.println("tarif_unitaire=");
					float tarif_unitaire = scanner.nextFloat();

					try {
						daos.getLigneCommandeDAO().create(new MLigneCommande(id_commande, id_produit, quantite, tarif_unitaire));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("Id_commande = ");
					scanner.nextInt();
					try {
						daos.getLigneCommandeDAO().delete(new MLigneCommande(scanner));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					try {
						daos.getLigneCommandeDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("cette ligne commande n'existe pas");
						e.printStackTrace();
					}

				} else {
					System.out.println("invalide");
				}


			}
		} else if (option == 2) {
			DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
			System.out.println("choisir une partie");
			System.out.println("1: Categorie 2: Produit 3: Client 4: Commande 5: Ligne de commande");
			int partie = scanner.nextInt();
			// categorie
			if (partie == 1) {
				System.out.println("Cat�gorie");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("Id categorie=");
					int idc = scanner.nextInt();
					System.out.println("titre=");
					String titre = scanner.next();
					System.out.println("visuel=");
					String visuel = scanner.next();
					try {
						daos.getCategorieDAO().create(new MCategorie(idc, titre, visuel));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id_categorie=");
					int id = scanner.nextInt();
					System.out.println("titre=");
					String titre = scanner.next();
					System.out.println("visuel=");
					String visuel = scanner.next();
					try {
						daos.getCategorieDAO().update(new MCategorie(id, titre, visuel));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id_categorie=");
					int id = scanner.nextInt();
					try {
						daos.getCategorieDAO().delete(new MCategorie(id));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_categorie=");
					int id = scanner.nextInt();
					try {
						daos.getCategorieDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("cette categorie n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}
			}


// Produit
			else if (partie == 2) {
				System.out.println("Produit");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("Id_produit");
					int idp = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("description=");
					String description = scanner.next();
					System.out.println("tarif=");
					float tarif = scanner.nextFloat();
					System.out.println("visuel=");
					String visuel = scanner.next();
					System.out.println("id cat�gorie=");
					int idc = scanner.nextInt();
					try {
						daos.getProduitDAO().create(new MProduit(idp, nom, description, tarif, visuel, idc));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id produit");
					int idp = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("description=");
					String description = scanner.next();
					System.out.println("tarif=");
					float tarif = scanner.nextFloat();
					System.out.println("visuel=");
					String visuel = scanner.next();
					System.out.println("id cat�gorie=");
					int idc = scanner.nextInt();
					try {
						daos.getProduitDAO().update(new MProduit(idp, nom, description, tarif, visuel, idc));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id produit=");
					scanner.nextInt();
					try {
						daos.getProduitDAO().delete(new MProduit(scanner));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_produit=");
					int id = scanner.nextInt();
					try {
						daos.getProduitDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("ce produit n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}


			}

// Client
			else if (partie == 3) {
				System.out.println("Client");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("prenom=");
					String prenom = scanner.next();
					System.out.println("identifiant=");
					String iden = scanner.next();
					System.out.println("mode de passe =");
					String mdp = scanner.next();
					System.out.println("adr_numero=");
					int adr = scanner.nextInt();
					System.out.println("adr_voie=");
					String adr_voie = scanner.next();
					System.out.println("adr_code_postal=");
					int adr_code_postal = scanner.nextInt();
					System.out.println("adr_ville=");
					String adr_ville = scanner.next();
					System.out.println("adr_pays=");
					String adr_pays = scanner.next();
					try {
						daos.getClientDAO().create(new MClient(id, nom, prenom, iden, mdp, adr, adr_voie, adr_code_postal, adr_ville, adr_pays));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					System.out.println("nom=");
					String nom = scanner.next();
					System.out.println("prenom=");
					String prenom = scanner.next();
					System.out.println("identifiant=");
					String iden = scanner.next();
					System.out.println("mode de passe =");
					String mdp = scanner.next();
					System.out.println("adr_numero=");
					int adr = scanner.nextInt();
					System.out.println("adr_voie=");
					String adr_voie = scanner.next();
					System.out.println("adr_code_postal=");
					int adr_code_postal = scanner.nextInt();
					System.out.println("adr_ville=");
					String adr_ville = scanner.next();
					System.out.println("adr_pays=");
					String adr_pays = scanner.next();
					try {
						daos.getClientDAO().update(new MClient(id, nom, prenom, iden, mdp, adr, adr_voie, adr_code_postal, adr_ville, adr_pays));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					try {
						daos.getClientDAO().delete(new MClient(id));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_client=");
					int id = scanner.nextInt();
					try {
						daos.getClientDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("ce client n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}
			}


//commande
			else if (partie == 4) {
				System.out.println("Commande");
				System.out.println("Choisir une m�thode");
				System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("date_commande=");
					String date = scanner.next();
					DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate dateDebut = LocalDate.parse(date, formatage);
					System.out.println("id_client");
					int idcl = scanner.nextInt();
					try {
						daos.getCommandeDAOO().create(new MCommande(dateDebut, idcl));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					System.out.println("date_commande=");
					String date = scanner.next();
					DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate dateDebut = LocalDate.parse(date, formatage);
					System.out.println("id_client");
					int idcl = scanner.nextInt();
					try {
						daos.getCommandeDAOO().update(new MCommande(id, dateDebut, idcl));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					try {
						daos.getCommandeDAOO().delete(new MCommande(id));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					try {
						daos.getCommandeDAOO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("cette commande n'existe pas");
						e.printStackTrace();
					}
				} else {
					System.out.println("invalide");
				}

			}
//ligne de commande
			else if (partie == 5) {
				System.out.println("Ligne de commande");
				System.out.println("Choisir une m�thode");
				System.out.println("1: Ajouter 2: Modifier 3: Supprimer 4: Chercher");
				int p = scanner.nextInt();
				if (p == 1) {
					System.out.println("Ajouter");
					System.out.println("Id_commande = ");
					int id_commande = scanner.nextInt();
					System.out.println("id_produit = ");
					int id_produit = scanner.nextInt();
					System.out.println("quantite = ");
					int quantite = scanner.nextInt();
					System.out.println("tarif_unitaire=");
					float tarif_unitaire = scanner.nextFloat();

					try {
						daos.getLigneCommandeDAO().create(new MLigneCommande(id_commande, id_produit, quantite, tarif_unitaire));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (p == 2) {
					System.out.println("Modifier");
					System.out.println("Ajouter");
					System.out.println("Id_commande = ");
					int id_commande = scanner.nextInt();
					System.out.println("id_produit = ");
					int id_produit = scanner.nextInt();
					System.out.println("quantite = ");
					int quantite = scanner.nextInt();
					System.out.println("tarif_unitaire=");
					float tarif_unitaire = scanner.nextFloat();

					try {
						daos.getLigneCommandeDAO().create(new MLigneCommande(id_commande, id_produit, quantite, tarif_unitaire));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 3) {
					System.out.println("Supprimer");
					System.out.println("Id_commande = ");
					scanner.nextInt();
					try {
						daos.getLigneCommandeDAO().delete(new MLigneCommande(scanner));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (p == 4) {
					System.out.println("Chercher");
					System.out.println("id_commande=");
					int id = scanner.nextInt();
					try {
						daos.getLigneCommandeDAO().getById(id);
						;
					} catch (Exception e) {
						System.out.println("cette ligne commande n'existe pas");
						e.printStackTrace();
					}

				} else {
					System.out.println("invalide");
				}


			}


		}
	}
}
*/