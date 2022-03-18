package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeText()
	{
		UIManager.put("OptionPane.cancelButtonText","İptal");
		UIManager.put("OptionPane.noButtonText","Hayır");
		UIManager.put("OptionPane.okButtonText","Tamam");
		UIManager.put("OptionPane.yesButtonText","Evet");
	}
	
	public static void shwMsg(String str)
	{
		String text;
		optionPaneChangeText();
		switch(str)
		{
		case "fill": text="Lutfen Tum Alanlari Doldurunuz.";
		break;
		case "success":
			text="İşlem Başarıyla Gerçekleştirilmiştir.";
			break;
		case "error":
			text="Bir Hata Gerçekleşti!";
			break;
		default: text=str;
		}
		
		JOptionPane.showMessageDialog(null, text, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str)
	{
		String msg;
		optionPaneChangeText();
		switch(str) {
		case "sure": msg="Bu İşlemi Gerçekleştirmek İstiyor Musun? ";
		break;
		default:
			msg=str;
			break;
				
		}
		int res= JOptionPane.showConfirmDialog(null, msg,"Dikkat !",JOptionPane.YES_NO_OPTION);
		if(res==0)
		{
			return true;
		}
		else
			return false;
	}
	
}
