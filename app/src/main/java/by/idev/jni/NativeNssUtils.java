package by.idev.jni;

//import java.util.Calendar;

//import ru.lissi.signmaker.ModalDialog;
//package com.example.test1;

import android.util.Log;

public class NativeNssUtils {
	static {
		System.loadLibrary("nspr4");
		System.loadLibrary("plc4");
		System.loadLibrary("plds4");
		System.loadLibrary("nssutil3");
		System.loadLibrary("freebl3");
		System.loadLibrary("sqlite3");
		System.loadLibrary("nssdbm3");
		System.loadLibrary("softokn3");

		System.loadLibrary("ls11sw");
		System.loadLibrary("wrapnss");
		System.loadLibrary("ls_rtpkcs11ecp");
	//	System.loadLibrary("rtpkcs11ecp");


		
	}
	/* Статический нативный метод
	 * свойства wrapper NSS
	 * @param prop_name имя свойства (need_token, wrapper_type, about, copyriht), которое нужно вернуть
	 * @return строку со значением свойства, либо пустая строка
	 */
	public static native String GetPluginProperty(String prop_name);

	/* Статический нативный метод
	 * путь к хранилищу NSS
	 * @return строку с путем
	 */
	public static native String GetConfigDir();

	/* Статический нативный метод
	 * список подключенных токенов
	 * @return массив строк с именами токенов
	 */

	public static native String[] ListToken();
		
	/* Статический нативный метод
	 * добавить модуль PKCS11
	 * @param sToken символическое имя токена, sLibrary путь к библиотеки
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int  AddModulePKCS11 (String sToken, String sLibrary);

	/* Статический нативный метод
	 * список модулей PKCS11
	 * @return массив строк с именами модулей
	 */
	public static native String[]  ListModulePKCS11();

	/* Статический нативный метод
	 * удалить модуль PKCS11
	 * @param sToken  имя модуля
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int DeleteModulePKCS11 (String sToken);

	/* Статический нативный метод
	 * получить список личных сертификатов с токена
	 * @param sToken  токена
	 * @return массив строк  личных сертификатов
	 */
	public static native String[] PrivCert(String sToken);

	/* Статический нативный метод
	 * получить список всех сертификатов с токена
	 * @param sToken  токена
	 * @return массив строк сертификатов
	 */
	public static native String[] AllCert (String sToken);

	/* Статический нативный метод
	 * добавить сертификат на токен
	 * @param sToken  имя токена, isFile - true из файла, false из строки, sContent сертификат, sTrusts - доверенный/тип сертификата
	 * @return строка с nickname сертификата
	 */
	public static native String  AddCert(String sToken, String sTrusts, String sContent, boolean isFile);

	/* Статический нативный метод
	 * удалить сертификат
	 * @param sCertNickname nickname сертификата, isPrivane - true личный сертификат, false другой, sContent сертификат
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int DeleteCert (String sCertNickname, boolean isPrivate);

	/* Статический нативный метод
	 * получить информацию о сертификате
	 * @param sCertNickname  nickname сертификата
	 * @return массив строк со свойствами сертификата
	 */
	public static native String[]  GetCertInfo(String sCertNickname);

	/* Статический нативный метод
	 * получить сертификат
	 * @param sCertNickname  nickname сертификата
	 * @return строка с сертификатом (PEM)
	 */
	public static native String GetCertContent(String sCertNickname);

	/* Статический нативный метод
	 * зашифровать файл (PKCS7)
	 * @param sCertNickname nickname сертификата для кого шифруем, sInFile - входной файл, sOutFile - выходной файл
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int P7EnvFile (String sCertNickname, String sInFile, String sOutFile);

	/* Статический нативный метод
	 * зашифровать строку (PKCS7)
	 * @param sCertNickname nickname сертификата для кого шифруем, sInText - входная строка
	 * @return строка с зашифрованным текстом
	 */
	public static native String P7EnvText  (String sCertNickname, String sInText);

	/* Статический нативный метод
	 * получить контент зашифрованного/подписанного файл (PKCS7)
	 * @param sInFile - входной файл, sOutFile - выходной файл
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int P7ContentFile(String sInFile, String sOutFile);

	/* Статический нативный метод
	 * получить контент зашифрованной/подписанной строки (PKCS7)
	 * @param sInTex - зашированная/подписанная строка
	 * @return строка с сонтентом 
	 */
	public static native String P7ContentText(String sInText);

	/* Статический нативный метод
	 * подписать файл (PKCS7)
	 * @param sCertNickname nickname сертификата подписи, sInFile - входной файл, sOutFile - выходной файл, isAttached true присоединенная подпись
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int P7SignFile(String sCertNickname, String sInFile, String sOutFile, boolean isAttached);

	/* Статический нативный метод
	 * подписать строку строку (PKCS7)
	 * @param sCertNickname nickname сертификата подписи, sInText - входная строка, isAttached true присоединенная подпись
	 * @return строка с подписью
	 */
	public static native String P7SignText      (String sCertNickname, String sInText, boolean isAttached);

	/* Статический нативный метод
	 * проверить подпись файла (PKCS7)
	 * @param sCertNickname nickname sInFile - исходный файл, sSignFile - файл с подписью
	 * @return массив строк: дата подписания и сертификат подписанта
	 */
	public static native String[]  P7VerifyFile(String sInFile, String sSignFile);

	/* Статический нативный метод
	 * проверить подпись строки (PKCS7)
	 * @param sCertNickname nickname sInStr - исходная строка, sSignStr - строка с подписью
	 * @return массив строк: дата подписания и сертификат подписанта
	 */
	public static native String[] P7VerifyText(String sInStr, String sSignStr);

	/* Статический нативный метод
	 * импорт PKCS12
	 * @param sToken токен для установки  sContent - файл PKCS12
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int P12ImportFile(String sToken, String sContent);

	/* Статический нативный метод
	 * Запрос PKCS10
	 * @param sToken токен ключевой пары, sSubject, SKeyUsage назначение ключа, sKeyParams 0-5, OutFile - файл для сохранения запроса
	 * @return 0 в случае успеха или код ошибки в противном случае
	 */
	public static native int CreateCertReq(String sToken, String sSubject, String sKeyUsage, String OutFile, long sKeyParams, boolean sPem);

	/* Статический нативный метод
	 * TLS-соединение с двусторонней аутентификацией
	 * @param sCertNickname nickname sHost - хост, sPortNo - номер порта, sInStr - запрос
	 * @return строка с ответом
	 */
	public static native String TLSClient(String sCertNickname, String sHost, String sPortNo, String sInStr);
	
	public static native int StartInitialize( String prompt );
	public static native int ExportPKCS12ToFile(String sToken, String sCertNickname, String sOutFile);
	public static native int UnpackSMIME(String sInFile, String sOutFile);
	public static native String[] CertOrReqView(String sCertNickname, boolean CertOrReq);
	
	public static int StartInitializeNss()
	{
		String sPrompt = "Prompt from NativeNssUtils";

		return StartInitialize( sPrompt );
	}

	public static String getPassword( String sPrompt )
	{
		boolean  b = sPrompt.indexOf("PKCS12")>=0;
		String ret = "01234567";
		String TAG = "myLogs";
		if(b == true){
			ret = "1234567890";
			Log.d(TAG, "P12Import Password == " + sPrompt);
			return ret;
		}


	//	ModalDialog mdlg = new ModalDialog();
		
		//ret = mdlg.showInputDialog( null, sPrompt );

		return ret;
	}

}
/*
		std::string     SelectDir       ();
		std::string     SelectFile      ();
		std::string     ReadFile        (const std::string &sFileName);
		int             P7Envelop       (const std::string &sContent, std::string &sResult, const std::string &sCertNickname, bool isStr, bool isPEM);
		int             P7Content       (const std::string &spkcs7, std::string &sresult, bool isStr);
		int             P7Sign          (const std::string &sContent, std::string &sResult, const std::string &sCertNickname, bool isAttached, bool isStr, bool isPEM);
		int             P7Verify        (const std::string &sSignature, const std::string &sContent, FB::VariantList &mResult, bool isStr);
		int             P12Import       (const std::string &sToken, const std::string &sContent, bool isStr);
*/

		


