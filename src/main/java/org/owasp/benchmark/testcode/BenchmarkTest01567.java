/**
* OWASP Benchmark Project v1.2beta
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Dave Wichers <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest01567")
public class BenchmarkTest01567 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		String[] values = request.getParameterValues("vector");
		String param;
		if (values != null && values.length > 0)
		  param = values[0];
		else param = "";

		String bar = new Test().doSomething(param);
		
		// Code based on example from:
		// http://examples.javacodegeeks.com/core-java/crypto/encrypt-decrypt-file-stream-with-des/
	    // 8-byte initialization vector
	    byte[] iv = {
	    	(byte)0xB2, (byte)0x12, (byte)0xD5, (byte)0xB2,
	    	(byte)0x44, (byte)0x21, (byte)0xC3, (byte)0xC3033
	    };
		
		try {
		    java.util.Properties benchmarkprops = new java.util.Properties();
		    benchmarkprops.load(this.getClass().getClassLoader().getResourceAsStream("benchmark.properties"));
			String algorithm = benchmarkprops.getProperty("cryptoAlg2", "AES/ECB/PKCS5Padding");
			javax.crypto.Cipher c = javax.crypto.Cipher.getInstance(algorithm);

            // Prepare the cipher to encrypt
            javax.crypto.SecretKey key = javax.crypto.KeyGenerator.getInstance("DESede").generateKey();
            java.security.spec.AlgorithmParameterSpec paramSpec = new javax.crypto.spec.IvParameterSpec(iv);
//            c.init(javax.crypto.Cipher.ENCRYPT_MODE, key, paramSpec);
            c.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
			
			// encrypt and store the results
			byte[] input = { (byte)'?' };
			Object inputParam = bar;
			if (inputParam instanceof String) input = ((String) inputParam).getBytes();
			if (inputParam instanceof java.io.InputStream) {
				byte[] strInput = new byte[1000];
				int i = ((java.io.InputStream) inputParam).read(strInput);
				if (i == -1) {
					response.getWriter().println("This input source requires a POST, not a GET. Incompatible UI for the InputStream source.");
					return;
				}
				input = java.util.Arrays.copyOf(strInput, i);
			}
			byte[] result = c.doFinal(input);
			
			java.io.File fileTarget = new java.io.File(
					new java.io.File(org.owasp.benchmark.helpers.Utils.testfileDir),"passwordFile.txt");
			java.io.FileWriter fw = new java.io.FileWriter(fileTarget,true); //the true will append the new data
			    fw.write("secret_value=" + org.owasp.esapi.ESAPI.encoder().encodeForBase64(result, true) + "\n");
			fw.close();
			response.getWriter().println("Sensitive value: '" + org.owasp.esapi.ESAPI.encoder().encodeForHTML(new String(input)) + "' encrypted and stored<br/>");
			
		} catch (java.security.NoSuchAlgorithmException e) {
			response.getWriter().println("Problem executing crypto - javax.crypto.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
			e.printStackTrace(response.getWriter());
			throw new ServletException(e);
		} catch (javax.crypto.NoSuchPaddingException e) {
			response.getWriter().println("Problem executing crypto - javax.crypto.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
			e.printStackTrace(response.getWriter());
			throw new ServletException(e);
		} catch (javax.crypto.IllegalBlockSizeException e) {
			response.getWriter().println("Problem executing crypto - javax.crypto.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
			e.printStackTrace(response.getWriter());
			throw new ServletException(e);
		} catch (javax.crypto.BadPaddingException e) {
			response.getWriter().println("Problem executing crypto - javax.crypto.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
			e.printStackTrace(response.getWriter());
			throw new ServletException(e);
		} catch (java.security.InvalidKeyException e) {
			response.getWriter().println("Problem executing crypto - javax.crypto.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
			e.printStackTrace(response.getWriter());
			throw new ServletException(e);
//		} catch (java.security.InvalidAlgorithmParameterException e) {
//			response.getWriter().println("Problem executing crypto - javax.crypto.Cipher.getInstance(java.lang.String,java.security.Provider) Test Case");
//			e.printStackTrace(response.getWriter());
//			throw new ServletException(e);
		}
		
		response.getWriter().println("Crypto Test javax.crypto.Cipher.getInstance(java.lang.String) executed");
	}  // end doPost

    private class Test {

        public String doSomething(String param) throws ServletException, IOException {

		// Chain a bunch of propagators in sequence
		String a89202 = param; //assign
		StringBuilder b89202 = new StringBuilder(a89202);  // stick in stringbuilder
		b89202.append(" SafeStuff"); // append some safe content
		b89202.replace(b89202.length()-"Chars".length(),b89202.length(),"Chars"); //replace some of the end content
		java.util.HashMap<String,Object> map89202 = new java.util.HashMap<String,Object>();
		map89202.put("key89202", b89202.toString()); // put in a collection
		String c89202 = (String)map89202.get("key89202"); // get it back out
		String d89202 = c89202.substring(0,c89202.length()-1); // extract most of it
		String e89202 = new String( new sun.misc.BASE64Decoder().decodeBuffer( 
		    new sun.misc.BASE64Encoder().encode( d89202.getBytes() ) )); // B64 encode and decode it
		String f89202 = e89202.split(" ")[0]; // split it on a space
		org.owasp.benchmark.helpers.ThingInterface thing = org.owasp.benchmark.helpers.ThingFactory.createThing();
		String g89202 = "barbarians_at_the_gate";  // This is static so this whole flow is 'safe'
		String bar = thing.doSomething(g89202); // reflection

            return bar;
        }
    } // end innerclass Test

} // end DataflowThruInnerClass
