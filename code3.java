String msg = "fwowo1867fwdmcls1703palw516jqgong4081sf";
for (int i = 0; i < msg.length(); i++) {
	if (Character.isDigit(msg.charAt(i))) {
		StringBuffer buf = new StringBuffer(msg.charAt(i));
		while (i < msg.length() && Character.isDigit(msg.charAt(i))) {
			buf.append(msg.charAt(i));
			i++;
		}
		int number = Integer.parseInt(buf.toString());
		System.out.print((char) (number % 199));
	}
}
System.out.println();
// asymptotic time complexity: O(n)