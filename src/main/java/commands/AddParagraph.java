//package commands;
//
//import GameBook.MainPresentationModel;
//import consoles.Console;
//import domains.Choice;
//import domains.Paragraph;
//
//public class AddParagraph extends Command{
//
//	final Console console;
//	final MainPresentationModel mpModel;
//
//	public AddParagraph(final Console console, final MainPresentationModel mpModel) {
//		super("add paragraph", "Adds a paragraph to the GameBook");
//		this.console = console;
//		this.mpModel = mpModel;
//	}
//
//	@Override
//	public void execute() {
//		String newParagraphIndex = console.readLine("Numéro du nouveau paragraphe ? ");
//
//		if (Integer.parseInt(newParagraphIndex) < 0) {
//			console.printLine("Impossible d'encoder un paragraphe avec un indice négatif !");
//		}else {
//			int newIndex = Integer.parseInt(newParagraphIndex);
//			if (Integer.parseInt(newParagraphIndex) > mpModel.getMaxParagraphIndex()) {
//				newIndex = mpModel.getMaxParagraphIndex()+1;
//			}
//			String paragraphText = console.readLine("Texte du nouveau paragraphe ? ");
//			mpModel.addParagraph(newIndex, new Paragraph(newIndex, paragraphText));
//			addChoices(newIndex);
//		}
//	}
//
//	private void addChoices(final int choiceOwner) {
//		String choiceText = "";
//		do {
//			choiceText = console.readLine("Texte de l’action possible (Enter si aucune) : ");
//			if (!"".equals(choiceText)) {
//				String destParagraph = console.readLine("Numéro du paragraphe de destination : ");
//				mpModel.checkExistingParagraphs(Integer.parseInt(destParagraph));
////				mpModel.addChoice(choiceOwner, new Choice(mpModel.getNextChoiceIndex(choiceOwner), choiceText, Integer.parseInt(destParagraph)));
//			}
//		} while (!choiceText.isBlank());
//	}
//}
