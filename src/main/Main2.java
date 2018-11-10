package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crypto.abe.api.Server;
import databaseAccess.DBConnection;
import sdm.*;

public class Main2 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("SDM Project");
		Server server = new Server();

		server.setMasterKey(
				"{\"MK\":\"RQAAABQdGaZYCNMHYVTsd163+DJQVOEsX0UAAACAQ7sxText4hQGbA2nnf+geYfVPWZi9Nyx3Hu7u91qlo0saEi1HkbpjXiJDzaMeVM4pKzwlEkHBCQLh2+d8vSElHNCOhkcOaReOET2BnAWvYkOc7FVAvxeKryeb5+40pWylP8KauqfiOVxwyoirqk97EYzSz5b3kFufR4rApSj2pU=\"}");
		server.setPublicKey(
				"{\"PK\":\"RQAAAIAJvAAqxjQ1pW2KBQp7gk1vYxM1vd7qSlCkOZVVnOP1RRiR+lPHfnbKPnPxKbB3vyjiLOHWQstjavAdmqW0ZcmaIndhMmubp+DdPDmRhu0HNtReoxPpT32mKnkfocwJZmK3q+deDCRvIwgVomLhBFMnfklafLeoSuF2BgrJ7kJR4EUAAACAR2fla9W32K5eJs1Tu1OcPvrGVsuu2ikyH4UToq5Hwrjz2iEbrE2B7phmMzHtFJITuY823MkBU5M7/Qp06oUxDQebW6YnYe6/NI9rYIGuPPA0tRPh5KfDfPkswAtiVq1K9Ypmv++r0hh3XalT1nubxvCJslfPDDqZ64kQc1fuuStFAAAAgDYJFBA7+vTiRFDH+V4XW87nCntw78GQFE2cNGQnsUpmMYq8JHAMQb6P4DehiSFBb9dUXvIukgY539SWaypV7+IECmFJd6/lYoSfzmqzbCYTZJcop7v57EhTWy6gznmZAi2oIT+ptmnyxY5MWXONQOPiK7zQSfCw9zB77E3T4q6ZRQAAAICbI5xrtHE9qV5Bip9K4xHk6TlVAmK2UGi2DOZ/jyvgYY5aSnLmq5bFQHyc14DK0zDxwnGIa16kynThtZE6f9O6epJuDRaXxMZEz5hm8KfNAjII91BA16pNiFOZfZfKw/9rPptQN6E35LAkEbL4Mm0KoxI4ut1NHmdtDE7acmJijQ==\"}");

		Person p1 = new Person(1, "Name1");
		Person p2 = new Person(2, "Name2");
		Person p3 = new Person(3, "Name3");
		Person p4 = new Person(4, "Name4");
		Person p5 = new Person(5, "Name5");
		Person p6 = new Person(6, "Name6");
		Person p7 = new Person(7, "Name7");
		Person p8 = new Person(8, "Name8");
		Person p9 = new Person(9, "Name9");
		Person p10 = new Person(10, "Name10");
		Person p11 = new Person(11, "Name11");
		Person p12 = new Person(12, "Name12");
		Person p13 = new Person(13, "Name13");
		Person p14 = new Person(14, "Name14");
		Person p15 = new Person(15, "Name15");
		Person p16 = new Person(16, "Name16");
		Person p17 = new Person(17, "Name17");
		Person p18 = new Person(18, "Name18");
		
		 p1.setAttrs(new String[] { "id1", "employee", "Company1" });
		 p2.setAttrs(new String[] { "id2", "employee", "Company2" });
		 p3.setAttrs(new String[] { "id3", "employee", "HealthClub1" });
		 p4.setAttrs(new String[] { "id4", "employee", "HealthClub2" });
		 p5.setAttrs(new String[] { "id5", "employee", "Insurance1" });
		 p6.setAttrs(new String[] { "id6", "employee", "Insurance2" });
		 p7.setAttrs(new String[] { "id7", "employer", "Company1" });
		 p8.setAttrs(new String[] { "id8", "employer", "Company2" });
		 p9.setAttrs(new String[] { "id9", "employer", "HealthClub1" });
		 p10.setAttrs(new String[] { "id10", "employer", "HealthClub2" });
		 p11.setAttrs(new String[] { "id11", "employer", "Insurance1" });
		 p12.setAttrs(new String[] { "id12", "employer", "Insurance2" });
		 p13.setAttrs(new String[] { "id13", "doctor", "Hospital1" });
		 p14.setAttrs(new String[] { "id14", "doctor", "Hospital2" });
		 p15.setAttrs(new String[] { "id15", "doctor", "Hospital3" });
		 p16.setAttrs(new String[] { "id16", "nurse", "Hospital1" });
		 p17.setAttrs(new String[] { "id17", "nurse", "Hospital2" });
		 p18.setAttrs(new String[] { "id18", "nurse", "Hospital3" });

		String PKJSONString = server.getPublicKeyInString();

		p1.setPK(PKJSONString);
		p2.setPK(PKJSONString);
		p3.setPK(PKJSONString);
		p4.setPK(PKJSONString);
		p5.setPK(PKJSONString);
		p6.setPK(PKJSONString);
		p7.setPK(PKJSONString);
		p8.setPK(PKJSONString);
		p9.setPK(PKJSONString);
		p10.setPK(PKJSONString);
		p11.setPK(PKJSONString);
		p12.setPK(PKJSONString);
		p13.setPK(PKJSONString);
		p14.setPK(PKJSONString);
		p15.setPK(PKJSONString);
		p16.setPK(PKJSONString);
		p17.setPK(PKJSONString);
		p18.setPK(PKJSONString);

		 String SKJSONString = server.generateSecretKey(p1.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p2.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p3.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p4.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p5.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p6.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p7.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p8.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p9.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p10.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p11.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p12.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p13.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p14.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p15.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p16.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p17.getAttrs());
		 System.out.println(SKJSONString); SKJSONString =
		 server.generateSecretKey(p18.getAttrs());
		 System.out.println(SKJSONString);
	
		p1.setSK(
				"{\"SK\":\"RQAAAICPs2F9GXq7sgEJw3IVicYGWeO2jJMW+yoWnlwq4bdaeYxIC6+LeqbhrLwmlTW+tKudfCksl9jFgHm4pok72hWuFKe+CcGM0HOwuYyIRFdqAyZrcbEiNR2Asm/8WEA99uzAmG3udvMBzPoBQhvywbGaJFlfcdcn1odcL3c7dIoSJFsAAAADUwADaWQxRQAAAIAYhESgDM+BpN2kIru9hzde/Aav/OL9dM8qBBXXFnhjjpE2KneD2oAO6LQFS2rsHSTE61OqosLGVUts+fmdysl7Rd0M1axOHx35j5baOpeCrP1WQqLUOGyW2fhvIlPOe8AjjXH2XuvTPzf0Lsb8HqH74/RNNGXG5gJnoJg87pMTEkUAAACAdAurK/IZWmYr/MFmpVAo8VxlwOYHPjGlKYkkxvN76NW7sNdIdWC1ZNHsoduMbtTLyYJlNbahJ2PWc0jThlPeAz9qPcwvQQxIuinDzHs+4i5YU1MSGJ25r3E7TQfM3jP+BRpXybj33F6c1F6jbFkXg1Lwl6SqZV1vxmeGi3thJndTAAhlbXBsb3llZUUAAACAGIREoAzPgaTdpCK7vYc3XvwGr/zi/XTPKgQV1xZ4Y46RNip3g9qADui0BUtq7B0kxOtTqqLCxlVLbPn5ncrJe0XdDNWsTh8d+Y+W2jqXgqz9VkKi1Dhsltn4byJTznvAI41x9l7r0z839C7G/B6h++P0TTRlxuYCZ6CYPO6TExJFAAAAgHX6Io2Hl+181Yvo1v9hKwm2/csnTFWubhLh5/vWE8vhXKDjSxrW5FNupbWlS/k35/T8fvoInzwTzNhg3VcpQ6NoGjWfVD9GO+mlBQgD/MoA9+bhcqlHFkdYZm/NsR+NpPtMWb0yXWyIE0pTnmMfcC0mJjg0mmUe0cCY/ePbm8E/UwAIQ29tcGFueTFFAAAAgBiERKAMz4Gk3aQiu72HN178Bq/84v10zyoEFdcWeGOOkTYqd4PagA7otAVLauwdJMTrU6qiwsZVS2z5+Z3KyXtF3QzVrE4fHfmPlto6l4Ks/VZCotQ4bJbZ+G8iU857wCONcfZe69M/N/Quxvweofvj9E00ZcbmAmegmDzukxMSRQAAAIBC4LhsMVxpWe2b7tFKMCUULgVl5iiLNi+fi3bARtAUFAavOwTRRpE+zb8EE+PXq8xy/nBvKarQ9z3Y4SLEBzI+d8pg+bhABm6KbB3/LvCb7R6hEPOanpotRCZ9G0bY5eWd2xgA9wD+joAXwt18CCivzmxvr03Mr2mwaOxGuyKasw==\"}");
		System.out.println(p1.getSK());
		p2.setSK(
				"{\"SK\":\"RQAAAIAjBK2sXCv6rqndQA/gkB+QU54QdkJDNR8dc9e7xUHfOMUGhTL1yTk/mMwmE/hgq6ONgvWkd0T9oIc96w66c0BaV66fGInnQ4owFTNQARgibPLr2SqZk270ThpNB7dn1ZwJPmfVllz/BLRCcFKVTvsNy6XmM/haS6FmImcQY8MGZ1sAAAADUwADaWQyRQAAAIA6L/IoRpHGmhOqqmdVZNvQ2rG+KKkIsd//cRsjZCPS8kWB7DmdAKwUlBub3toqIuqy9K57MKeInX0N90TuBNFUgABIwdmeiTk5bvqOrMu81wzo4cT7dxTzirIenQOUxItfpyHNxNZHf8MxXAcxeBrxr1VwWNhKmd5oj/NoQMLcLkUAAACAKMlQozHqeek9p9mueFP6JywuT9zzpIhzeiW9+WvZTbQbNkAwRDk0Ad/IDTxjCybyqqREZ3Z/Q+SYWfgnZ0UbQzdv9MeMbedl2DAgSrpAQ4DRVH9JF5h4f4VK8lwciyh9qIglFQznPUtOFAtIxVvVe1oEkDY9b4TXehxr9dxVlLFTAAhlbXBsb3llZUUAAACAOi/yKEaRxpoTqqpnVWTb0NqxviipCLHf/3EbI2Qj0vJFgew5nQCsFJQbm97aKiLqsvSuezCniJ19DfdE7gTRVIAASMHZnok5OW76jqzLvNcM6OHE+3cU84qyHp0DlMSLX6chzcTWR3/DMVwHMXga8a9VcFjYSpneaI/zaEDC3C5FAAAAgELakECxhwQQODQB8eWMEIcyflJUf0JlJDIryQeCZfRAG5RcyD9bMH3yszT2/hkkrbziDCd1VXriHpb4RVi5U1dcxqi9muP9l1H2hXOxedyqS60b63FffTTGOEa0pNjskVAsyQyC5uHl5hZcu1brEAoQod1r/3QVEN0PWy5T6hIcUwAIQ29tcGFueTJFAAAAgDov8ihGkcaaE6qqZ1Vk29Dasb4oqQix3/9xGyNkI9LyRYHsOZ0ArBSUG5ve2ioi6rL0rnswp4idfQ33RO4E0VSAAEjB2Z6JOTlu+o6sy7zXDOjhxPt3FPOKsh6dA5TEi1+nIc3E1kd/wzFcBzF4GvGvVXBY2EqZ3miP82hAwtwuRQAAAIBNfIhLwCMLQoRqC0Hky4+YWms9ldIAsAH4v5FtyJtoYORWiW1xoBX+DdLlD5GIxb9Gq/Y+SHFo6lnUF3OqhzYSLeb2XuP8oahQeyMk32zucI4phte7FFM80013V0iUnG+4vjtXSD4EzBCtfGVt8o5f4NgkxQAqhoqUKMKaWopLDQ==\"}");
		System.out.println(p2.getSK());
		p3.setSK(
				"{\"SK\":\"RQAAAIBREIUMTNrJwFTEIuZWKykgcuN1b+P7W8TKcB9GvKanvm05wnMF9WK35fKx47ID/v0h55urKO7Ua28DT5tOBtZOXc2e2ObVLPWitH2JQgphalY6z3I/+OZWCIsxc9p1OgkOPxglD0KFtd42RVaiQSoMTM78uF7B7PaAUhZh47qRbVsAAAADUwADaWQzRQAAAIBPJhv1He/k426iZdUnusBcegebGZSo5tCl1tLy/mIbKuGWd+mDisxJAIGa7Y14vSmiLOMrcUNz0aZzzMFHhfdIp3nx34wym7xMGy74o8DKogX9FRs9LF3QrZ8O+RxjbEmhJo7bEgDYUj6kneGBqbwQe2libv9VCGbunQRpEcwM9UUAAACAoZ1SjK+CCIwJXNDXSTipiI0SP6w33kyrMwZ5y8JOw6QeQaHchrYJjEMFdt1Z0megu+ulI7uAlAwHUTmwonpoDGyT6hAM184KWOmyZVx3W9oBNJZBdzbMO1iVVfdmAXzYAMPVJI6y/WPltPg3oU3OHuJoKFja1zo9/+hDOsnMgMtTAAhlbXBsb3llZUUAAACATyYb9R3v5ONuomXVJ7rAXHoHmxmUqObQpdbS8v5iGyrhlnfpg4rMSQCBmu2NeL0poizjK3FDc9Gmc8zBR4X3SKd58d+MMpu8TBsu+KPAyqIF/RUbPSxd0K2fDvkcY2xJoSaO2xIA2FI+pJ3hgam8EHtpYm7/VQhm7p0EaRHMDPVFAAAAgH2DLsM8b07v6EURrshMBhoil2QLkXjcf8MZ1V621l41Ug/Mmymx/Haf+zwyQs5uxnwTXhID+eksXKRDbvs4qAYB4fqi2clM3SCBjx4Lp6wXo5XHMVWwpXKEzpT2taXFqPcVgEmSNrLqGlMPTYbAIOHk5VeyTZtb5OZ2+sOceN9cUwALSGVhbHRoQ2x1YjFFAAAAgE8mG/Ud7+TjbqJl1Se6wFx6B5sZlKjm0KXW0vL+Yhsq4ZZ36YOKzEkAgZrtjXi9KaIs4ytxQ3PRpnPMwUeF90inefHfjDKbvEwbLvijwMqiBf0VGz0sXdCtnw75HGNsSaEmjtsSANhSPqSd4YGpvBB7aWJu/1UIZu6dBGkRzAz1RQAAAIAxpRSEr48XONXAVIMMaAUpZiOTg9jVt0UAbi5WCY5/nGlgcIORFJBcfDNGhKXKKXQPRCTsUiT0puefwfbuZ9iIn3moq9UdaRyV9dRTGGgmgEGjQXHUfwkruG/MnPQDHq5DUNZXvW/Ovo60p3LIDz2ZraRAo3aOxCQxIZng+zlm7g==\"}");
		System.out.println(p3.getSK());
		p4.setSK(
				"{\"SK\":\"RQAAAIAzHY26WPHnjucZh7tMQvwlekhx3qPuXjggs4U5CZ7r1ju9p64V7bNDytVfLZF88lZjncD7ZEpWZbYJFCF4UYiTOCmxludy+cVxt9RBuflcV7/jDY4BSTdkjMSVapfjS2GNlDG1WXUEfo5qV/Ih7Z318T+fY60I4x3miMe5X0PrYFsAAAADUwADaWQ0RQAAAICNFk7iEWeZUIE2hi+q7UtLIDSMhLDOhI82YSSM1Uv/EGDWGH7sS2fW/4VTE1YIo6iIhI+9WDwWm4qaXDgD3siPJBa8KCeFtrMOxaSJiAfT7mQeTmPohe7uRGlK3EfDkJhnsa6tR5Fc1tT7AN3ZOQZz7kQr3kDdCwdIEIMDLduW5kUAAACAMW61pdZoyH0MwiAM5CyKqntsj/IaZs7jJI3GP9LRpFK1k8sHSiFCXuH8m0bin8RDT3VG8eKzJkKyv8hJof3Lqyy4vya7oKhIgaK2VwuTukBfOUOZ0MZx1WJouDrorrTCWGDxwLTHMSxaIabqBZJ8Oyq3UcdFjzCSREQvBCXTkbtTAAhlbXBsb3llZUUAAACAjRZO4hFnmVCBNoYvqu1LSyA0jISwzoSPNmEkjNVL/xBg1hh+7Etn1v+FUxNWCKOoiISPvVg8FpuKmlw4A97IjyQWvCgnhbazDsWkiYgH0+5kHk5j6IXu7kRpStxHw5CYZ7GurUeRXNbU+wDd2TkGc+5EK95A3QsHSBCDAy3bluZFAAAAgHRJz3BJnCujc0/OI9w8CcAcrRJqi91fhZyLixthKKcI5DTdF/IZBUk4db5qKZS1N1GviII0JHMmbwdmhUyruU2MS2kgni8imGxXn4Nez29gZvHjCA5h1tRIsNB756Ux88oGkLPo7A1cJJWmzgZeHYUMDLYWY2KNFIjgWO6bqmBzUwALSGVhbHRoQ2x1YjJFAAAAgI0WTuIRZ5lQgTaGL6rtS0sgNIyEsM6EjzZhJIzVS/8QYNYYfuxLZ9b/hVMTVgijqIiEj71YPBabippcOAPeyI8kFrwoJ4W2sw7FpImIB9PuZB5OY+iF7u5EaUrcR8OQmGexrq1HkVzW1PsA3dk5BnPuRCveQN0LB0gQgwMt25bmRQAAAICf4K3cpI/sAa3OB/jdDwp+k/H0pnlNAxzCibWhCA1tYzvIHkiHQkhkOhEjQKEK/z0gBf8EFuJbbD1QeAW/YKAjAqMP9c85bKWOzRdYhRVGVpvJVnET915WcLKKIy6xsEtqYoNBnaKEY0GKT//dUoNY4ECRNbOlqRdnhK5+E40q+g==\"}");
		System.out.println(p4.getSK());
		p5.setSK(
				"{\"SK\":\"RQAAAIAS6Puk0K5pamh0ZpMmpA2/+LU+yPDGMG0ARaXDk4EsDpOXELvUj8JEBerqxuOxewdElaqFkNoq9JmbQyaHF6gSpF+/F9jDhF6+YTKMHCkiLIW/GXmtmciZ8H6ankSN59ETxvf9MNSXrifDh13aAaCnUK9pWNtwPtq19K7kBXGzIlsAAAADUwADaWQ1RQAAAICRMVl82bK8NXid7Opq2sDUP3jkE6DKpijxbzYr74PBVtC7SzM9kIzF9sM39eWEMVVu6fOtDyeTi5RM7qkjgLTEdRddK1Z38U5gPdi0SQ1Q8cxWlxHNsQB+8as9Dg0ioHWCUT3PsCO6mYOpUQQMfTQyZHrQ9DbqeDw16J2Gk0FO+EUAAACASpsctoKro6F/g8k8gPpl8xqJHBfWpVEbyuW0dMDccYvdvqYKkrxawgQgigJpeavwMFeaTWMUwoppsrAopRQZNmMq4xZeFI6NiCp/XqmMOToyO5PA648VWMO9c5mFndbDus5Iq0qhTebYXDwpmVgG9esnKpHlROEGOHKgsOoMm1hTAAhlbXBsb3llZUUAAACAkTFZfNmyvDV4nezqatrA1D945BOgyqYo8W82K++DwVbQu0szPZCMxfbDN/XlhDFVbunzrQ8nk4uUTO6pI4C0xHUXXStWd/FOYD3YtEkNUPHMVpcRzbEAfvGrPQ4NIqB1glE9z7AjupmDqVEEDH00MmR60PQ26ng8NeidhpNBTvhFAAAAgGJTXxavQBuY02iMU3gGDKO0mkCpjAuKZ2YTLz/SGVa3zWq1uZyeRk8dwR4c5+Dns+99iyjKlxVcN7bhNHb0L5cjcO1L4onoh/dLDRYQpBrINXxvyZQQfNk5BpLlfaqM9ZfumCT4xuT+2tAHQdmEeF4U/cRJNKUyDHRD3kxls1zIUwAKSW5zdXJhbmNlMUUAAACAkTFZfNmyvDV4nezqatrA1D945BOgyqYo8W82K++DwVbQu0szPZCMxfbDN/XlhDFVbunzrQ8nk4uUTO6pI4C0xHUXXStWd/FOYD3YtEkNUPHMVpcRzbEAfvGrPQ4NIqB1glE9z7AjupmDqVEEDH00MmR60PQ26ng8NeidhpNBTvhFAAAAgDnm937gwmQkXFXMnd9RMfc3WqrLXx+aQqSba2GOWvKiwgLPbPT7aBDFjGCz38AJiVa5TfMeYdR5TYaC4Pq0u/2PB9cyedO8AzciXVpSjbKOkob/X3rYsFXGGfAAKMwPuUCxnOKwbyJcGclAnZy2zMQGKBaewhjPaKNTrG4lvZgB\"}");
		System.out.println(p5.getSK());
		p6.setSK(
				"{\"SK\":\"RQAAAIBMicdcjsvT0COXaVHxxnqMPEcSrXS6wpgwEfgjau2egcxoAtnqfKWAX7KKrSIP6Jya9n/lTUkHeRnAY7J2PmULfyu4M2StCJ3qfSzo6zyR5P/31o/15hZdhONLs1DVK3CTwakS6nBNTbva06aHuuqN+3lXVvZrKBWuBcCQCnuN8lsAAAADUwADaWQ2RQAAAIBTPvKJV7Iz0DDXoaPq+uvdSfvisxJuUrXuYwzNdAKALzyfoTssh2VqdkSbMatk1yBEz5zm7hBByMwpERTC/iUMR+r5C8ozrsqfMFCznfnFfG6bAiSHoodoJCeVDgqsHdFm5dnkGS/Tva8LEdf1RzztA8G86UMT7y6i5jsQLV2qWUUAAACAkZ30moP7Hr9UYuODAn2uZQfkQ8hQxmLVJcD+4rrs/LRmjsFxdBLX7+WTaBlcT3+GUkcwi2wzOPtYJsdBUf+4NQWs7JH4n8sHfGptprpyY+K94+YKxSNLCLiw2SZNkAe2s6whkrqNikqSA51bHvP80JBgZ/WRHDfkOlVbhzTdtyFTAAhlbXBsb3llZUUAAACAUz7yiVeyM9Aw16Gj6vrr3Un74rMSblK17mMMzXQCgC88n6E7LIdlanZEmzGrZNcgRM+c5u4QQcjMKREUwv4lDEfq+QvKM67KnzBQs535xXxumwIkh6KHaCQnlQ4KrB3RZuXZ5Bkv072vCxHX9Uc87QPBvOlDE+8uouY7EC1dqllFAAAAgD3mJ1CKBB5CvbtT4qd90m0TLQeKxRIwOA5QR1Js9aOMUA1REbPBAkihjm2N0td9PdEJrl+hl2y+0W1wsxbkcLtZJxhq9tnbTBsHKkvgN8jDJoXZ3oqRDpAyOKVusYpmOrbfvN2nQHYFVyL0uq8HhR3V2enWBswn/PHCoFU12CcFUwAKSW5zdXJhbmNlMkUAAACAUz7yiVeyM9Aw16Gj6vrr3Un74rMSblK17mMMzXQCgC88n6E7LIdlanZEmzGrZNcgRM+c5u4QQcjMKREUwv4lDEfq+QvKM67KnzBQs535xXxumwIkh6KHaCQnlQ4KrB3RZuXZ5Bkv072vCxHX9Uc87QPBvOlDE+8uouY7EC1dqllFAAAAgKNKJYWIXcC9gYIy7usomKSBYoGO/2jcHRTq4H6/LLck7WTZHTqv6QzcQhHt5tnksqTA3TS8PUAB4nG3Nw5vjNKcD9O2ZuJFiZS1GMhQ0XxLv5mK+cwzaYGXJda9vZke71/UARm3A/EM6vcNKS41bqAnUwRkbxkahWgywMOD7NPU\"}");
		System.out.println(p6.getSK());
		p7.setSK(
				"{\"SK\":\"RQAAAIB0WYz4mbiWJE2P90agg8oPb49STklrwWTsAxSPzdQ8w4vSBFluUPIbdjgMNiwatIKKFcgx5ysAVM2ZA92T/tt/bWIHjB3qwOj1OtDcrEwwiBBUjSy/cNJcPSRaCAUpnmQ4674tpgaGKhIZ5N8rfQObDx5v35Sxv1/PBet3YKc6cVsAAAADUwADaWQ3RQAAAIB1+WNYuQmoOAQnIGCkOaf9GDJWqa2EO2GyHSSfUXrK7dIqNzNJX9GB+fN0IrdN6wHte+iyRdAxVTeqqT9kqJeLeLgQ/q4C9fsdldHYre1K7sXRSl0Y9XaHA0nKyaUSkXhQFaV9ScaZWH94Oh7MyWH7Xj5/6V1xpdBS4kIxKLp4/0UAAACAjbo1ms3vl+mwVfHyg3k5KJfgKHg6/5jSFN4c1TF90N7oH2djWQovnEzbeeYQ8DHSVx4YD8y1HFbWuF06tik2+x4+grQs/39TQQ6ndADGZjKLgEeL4zm3gkjxjPO101//Az95swiHqpUfLb3uobrLTgxW8sEVprLW5YDgZlbd4MdTAAhlbXBsb3llckUAAACAdfljWLkJqDgEJyBgpDmn/RgyVqmthDthsh0kn1F6yu3SKjczSV/RgfnzdCK3TesB7XvoskXQMVU3qqk/ZKiXi3i4EP6uAvX7HZXR2K3tSu7F0UpdGPV2hwNJysmlEpF4UBWlfUnGmVh/eDoezMlh+14+f+ldcaXQUuJCMSi6eP9FAAAAgGRjc/wPsYVVgHwNO2fykWrsDQF2v0++RalNMWViXp589AehreGys7m6UYQVubGODK4U3VDn6Q/a7WkaIp3x8XSQgnBXm1F512UukYNyfVB0SNtmisTzjOdIzn3dSHcJTwVSOb51HW9hRaFAy146pxV/Se2no5y/ojRW7J/Q71I5UwAIQ29tcGFueTFFAAAAgHX5Y1i5Cag4BCcgYKQ5p/0YMlaprYQ7YbIdJJ9Resrt0io3M0lf0YH583Qit03rAe176LJF0DFVN6qpP2Sol4t4uBD+rgL1+x2V0dit7UruxdFKXRj1docDScrJpRKReFAVpX1JxplYf3g6HszJYftePn/pXXGl0FLiQjEounj/RQAAAIBKEqukUMQpnO55dZp45ZtVILyefhKmUN6doQlmuCmjZASFCiM0/eZtPr09mrJ8l+K/FtW6HRUBNDuGa/TqQg/yMsScHahI3xDnJQRRdk8owF2P1gApsPUdfw17BM8hRb/qLXRudWD1URXkeVdbnVc13g6Elff36z9+lqDObBrr1A==\"}");
		System.out.println(p7.getSK());
		p8.setSK(
				"{\"SK\":\"RQAAAIAC+51Ft7+Cm+Zjsxk7vIBLNN7sMquUJrjNiqaS9qLJq67+JF6REetYN6W3RHGwphqXffl6w86vKF6GxbuWEXB/JDL0ug2H6+loYiIYt0LwnoiZx/AK4Hab7P2JFPDrfXruKSGIC6e2vHGj1DExM/JET+pxwnGC35fhUvO8mzU7oFsAAAADUwADaWQ4RQAAAIAPiaH/820A4ueqtoJEgZpdcoAJZ1f9rIlpz/WkbmSA0dp2SFClFTk9Qc/Tpkx1Wzo8CZcNPjBdy1QmZeIrvfmXj3l7F1N4PTBsmaG+BZTkplakw219fR2wxn4gEtTNQzuchzhFhoMAMvKEftyjFa7gxfVqibrO30SDfjzTuzy9IkUAAACAj+tv28AHhd7WblavccGCM8CRhcY7JHz7Q3NxiekQnWfV78bkiGcx+88LpoWxlZnf1xswBCcxUyO2UKqv2Z/C7YhrWvtDBLE0e159HGKUcn7NIOGob6fGzZh0KLel1HJ54cuZH4t4CQGbrgGQT85pxMf0Z49U9Sa0OGav5QLiLeFTAAhlbXBsb3llckUAAACAD4mh//NtAOLnqraCRIGaXXKACWdX/ayJac/1pG5kgNHadkhQpRU5PUHP06ZMdVs6PAmXDT4wXctUJmXiK735l495exdTeD0wbJmhvgWU5KZWpMNtfX0dsMZ+IBLUzUM7nIc4RYaDADLyhH7coxWu4MX1aom6zt9Eg34807s8vSJFAAAAgDHuIdif/QvnHF+QHUK1sh7C0hXHyALNOjNFcvna0vjsnYoMvAqeWY8mwxcV7+RN4sN7wGz5FFG9VX9g6oOte6wb3bIeufgxg4hJwnksFJzL6hys+Qszw4MXtZ1+sqF13LAf6rNPXoMGXXbSc6YqnUEj6SibBOfwtX/qxmMZeU0RUwAIQ29tcGFueTJFAAAAgA+Jof/zbQDi56q2gkSBml1ygAlnV/2siWnP9aRuZIDR2nZIUKUVOT1Bz9OmTHVbOjwJlw0+MF3LVCZl4iu9+ZePeXsXU3g9MGyZob4FlOSmVqTDbX19HbDGfiAS1M1DO5yHOEWGgwAy8oR+3KMVruDF9WqJus7fRIN+PNO7PL0iRQAAAIB8MGW+vvw++blfOSau4CQZ+KGiZdNHSkrMcJTBvaD0ekTK2gx9oADzUikkyQLBKKXyzVNZ2gkiyGD6fZ3NE5O5an8uUZnAjpYYUc5j7LMkbi8iuX9I1onyd253QDwM6ivA8B9iFUtrGU3qJUX6v/5AGeWxUsL8G8TQHKzIDs+SUg==\"}");
		System.out.println(p8.getSK());
		p9.setSK(
				"{\"SK\":\"RQAAAICc6CFXMQ6i6rooF7xhREo8CWrN5RcRjq6/NcHsHxO4ZugoYy6CnOEy3u3Mzf/DiviGPFFk8jS2rqBOan5FPG7KJx2RA36R8NmZFUKT96kjQa0qTslpuCL93Gha9nexN9pW4Ao7LhGYUWvWT/a/tg5rGf3rUyhZsAw+PAzo+xqDDlsAAAADUwADaWQ5RQAAAICK/VscQkjoGAuEofkhN9rbEVUNQz9AmpXl/FGiGkyll75psk1GmP3zoPeADHHCCzW3/sFugdJDk9hPiZgGaZ15AsbAoaVRH0j2JPXquL9KKEFOeTuEEqNX50YEygx4cC7dNSKPoe7kG2DTnWuF38pBiGpHuvuXBPZ3VkFTejzNdUUAAACARb/xoDZByoi3naG/cY8/RyU6q6KsN+u9u0pbPvBZiClQiv6OXSB/lkunpM6Vbb5Q728YTkiXUL0keTyIdgdE7lMDJgWk+RP97+cTuDzR80BqM7wvPcfZGb1X7kVA4ZgxU7yHDX4D6NEIOdpXOxZ4GofDsFbsy7UXMhalQWsSmUJTAAhlbXBsb3llckUAAACAiv1bHEJI6BgLhKH5ITfa2xFVDUM/QJqV5fxRohpMpZe+abJNRpj986D3gAxxwgs1t/7BboHSQ5PYT4mYBmmdeQLGwKGlUR9I9iT16ri/SihBTnk7hBKjV+dGBMoMeHAu3TUij6Hu5Btg051rhd/KQYhqR7r7lwT2d1ZBU3o8zXVFAAAAgCAOZn2DV2QepUGOi9LuwS6KMaog4lrKKuU/OPFTeCtahyOy5ZsvIgf92VKOd61NykM9x8yHyxQBW96ZzLNG78allIhXVDOTbdfUjg29gEab34obiSUmu+w1AjQ+pIWbomEN6jmuRkCIcSeXSMfshAU6ZmJ1P2CLBmuFG8ATN+krUwALSGVhbHRoQ2x1YjFFAAAAgIr9WxxCSOgYC4Sh+SE32tsRVQ1DP0CaleX8UaIaTKWXvmmyTUaY/fOg94AMccILNbf+wW6B0kOT2E+JmAZpnXkCxsChpVEfSPYk9eq4v0ooQU55O4QSo1fnRgTKDHhwLt01Io+h7uQbYNOda4XfykGIake6+5cE9ndWQVN6PM11RQAAAICKBa5VnvYsVXJN9ii3wP/VbQvCI9eLEv3eh3KFuicFByLwuHe1bqgLw0vLgsODV7o2L/BXC8M3gc2uygS00QveZ7CpLz1jSJpU7w+3y3PMrUk9uRI+uQooHi7OJRos46MWFOuzFsCFuxBdwhl8ld0lhTf0Hb9j9RIOZulF08TyAw==\"}");
		System.out.println(p9.getSK());
		p10.setSK(
				"{\"SK\":\"RQAAAIAuB+87jlx6IauyYgugiqcbyek7h9rBrwG7heKI7xnsoi1JpmNtCqBZZZYiaCQvdURMyQgDa4yVlaJ2c65tvU9ZcyrPnCEL8bxqv2aRHQQiYXEsZad4Zv3ha4SgaL3stMPefnEJw9rG2OV6RpT+2RaPdz3FE+bU5RNe9E0T6xY6qVsAAAADUwAEaWQxMEUAAACAdiCMZYCf1JBc81Md7TevmkD+3Av1dRy/wI406zY7jZVcIarNC7d1H2Mh30ala/cvP/HZVlOz2OcnC3GAg8J2Ml+vOg55ymZFot5Svlq6DCL3E5mb2UkpGHG64ld3DiPSq5RXO1AGpyOmgOGzexOc0ik7K2dvVVtM446MLTH9vu5FAAAAgDKqcxbWTV9vTb4ZPOkHfo8BDng4563mprlNGf+WHVxGwlbtC5BTgL/QGAXWVmuiw4/uI954PWGw4yzyCtGch29UPEHur2XEFImd/R+vW//MNdt0H59U1/1KsS8YrS03pJMvAtCbquYUgcvmBq9LloD/NVTBQYgtXkcFBY2kD+qCUwAIZW1wbG95ZXJFAAAAgHYgjGWAn9SQXPNTHe03r5pA/twL9XUcv8CONOs2O42VXCGqzQu3dR9jId9GpWv3Lz/x2VZTs9jnJwtxgIPCdjJfrzoOecpmRaLeUr5augwi9xOZm9lJKRhxuuJXdw4j0quUVztQBqcjpoDhs3sTnNIpOytnb1VbTOOOjC0x/b7uRQAAAIApazdOM7o2WAuAlypxZiU4MZQWrXvAbsrbh2LBeqbSa4Hj3G6EYQPZ7S7WQaNz8AX+V6/KEiQGg6a/ItDI4QAyR2yJMJ0RaPNn88A/imQKQ9lYHjsBA3jo4u93cc2MoSr3Ctm5OX0eH7XVlI0KhhlEpOu6xCofvFpT/oHqPCVqblMAC0hlYWx0aENsdWIyRQAAAIB2IIxlgJ/UkFzzUx3tN6+aQP7cC/V1HL/AjjTrNjuNlVwhqs0Lt3UfYyHfRqVr9y8/8dlWU7PY5ycLcYCDwnYyX686DnnKZkWi3lK+WroMIvcTmZvZSSkYcbriV3cOI9KrlFc7UAanI6aA4bN7E5zSKTsrZ29VW0zjjowtMf2+7kUAAACAPhxh+9JUhJNEViv0lywoR2GmW0oB8tQ83YCihiu9nnOHanIHGip08xwaySQPXU657i1wOhVskFYt915l9tTR1RIa46jeYClVaF0hE4HqilmnhgQ6OgjEqm8ijW8aoRHFgQleLELPVIqL2PJ6zGCrUzPc8t0GkgrDEhnyhRfRg9E=\"}");
		System.out.println(p10.getSK());
		p11.setSK(
				"{\"SK\":\"RQAAAIBBeooe31Ih/JIf9IdsW/a7x/PBTA0yej51PEXRWumhEItm/fOcr6WJFtXXkuptbBqztZ1qH53TwdVc5ehvi8ZtWa7MN6/oIZw1lwa5oQs045JXnkDR+zt51rQSqVddOYAQjAha1wGohR2bEP6MdTc1MP1jwjDTZvb9tljGrUxjZVsAAAADUwAEaWQxMUUAAACANowYICIO/MzsBISRWNGGlBAj/R1sIx6uIKG6Jbj3nXNt+1AbUW4j0rUeNYDBTnCZ4rbhimNZXMRe3ax8sTZIDgxyL25/0hqM7ayM5j6XgELHXV8XvhqTGAKSSdY4JqmdltA0as6hZjImoz0jBHJHs0ydahBY5/Q3SGKzM8t69spFAAAAgDvpcF3egF/0CYADa8H18CBcrz7/GCmS9YATmeUUlLxCP+9aUunSDM9zUxwg1XTOmOeSYkl92OmwIt8keiMhqw5mbWVBSdMFxnbnPdnu8BQ/PtYn0jKUPZbJ957/cl2PN/9nsrI/Hlx9UaBU2pEgGXMvVTY8R0kJ+hU5aADcTTakUwAIZW1wbG95ZXJFAAAAgDaMGCAiDvzM7ASEkVjRhpQQI/0dbCMeriChuiW4951zbftQG1FuI9K1HjWAwU5wmeK24YpjWVzEXt2sfLE2SA4Mci9uf9IajO2sjOY+l4BCx11fF74akxgCkknWOCapnZbQNGrOoWYyJqM9IwRyR7NMnWoQWOf0N0hiszPLevbKRQAAAICPYvEM2aQl5/Pn6wxs8CHtSWkNK3fxCtgAHShlaLeSJc9TvO47SgqtC3fwpSnbue3mcEiL777vqmMaNqQZbO+gYmwUJQokhQUbm+oEXnXO81mHS7jEjsFwMzwiCb6G8jGtfh2RgPojZmPv+IR+TI8VHf89+CLbMCzmdwO0lJAS4FMACkluc3VyYW5jZTFFAAAAgDaMGCAiDvzM7ASEkVjRhpQQI/0dbCMeriChuiW4951zbftQG1FuI9K1HjWAwU5wmeK24YpjWVzEXt2sfLE2SA4Mci9uf9IajO2sjOY+l4BCx11fF74akxgCkknWOCapnZbQNGrOoWYyJqM9IwRyR7NMnWoQWOf0N0hiszPLevbKRQAAAIBjlGJ2aQtzeZ6JvPhiSSAh4yby7oI4g5JNqGJK7dvNJBpTi/ESkVOsEjlXj3fLlYqIFdOHAoVt1SbNKmIssMURHI1A2KAX/fMIxCc6fh7/Fvl8HEw5alh7BdCkX3zFPJ17r/aGmEeW1dv/sGmoz9380iDzZBffi9KLWlQE34AYxg==\"}");
		System.out.println(p11.getSK());
		p12.setSK(
				"{\"SK\":\"RQAAAICb1B+jrq6HAcoro6nUiU/NcjFmnMpJ4KfYgXeC5MEAOKUmSbhR2XgAGP53rbqrVhf3w2sYHZJxLQlJOJ9AV0ghh22wdlvPi0UXEasvtosdpk4zaKtnXMn1nPnA/ObUgWgrlYH3YS7yZVDSSVtBCGIHpo3OutZWLuo7c1RPJ9DgnVsAAAADUwAEaWQxMkUAAACACCZW96dznsAouj6gO6NOvsN4r3sV/VN9a/wrTJk7SeZDlA50maV0s6+JdSKCXTF1VmP3oTSYtxqnvy4S8Ebag5q1auSxhFDt5Tby2evNyb7R4h+Va5RPRXBMWsxLooFlX0CSF/Qd4HbymoOI7thvZIfLzMXO3kLtycctXMxBMdpFAAAAgGEMbs8axh1Y2DCNKh+dEEhlpZJjaACxGtkqX9/nRCp8rHapQ1nwtBQyEtgK9Lf+qWGlEDzkYCWQqIbtIjEWfAtpBhI5IODS47MiP90qwpOtQiuBTdEJR2KxG4Y6BVTdDtszBwM+1Lvol3qpsLfr5wIGZT1E5v6myw1LnyYqi06JUwAIZW1wbG95ZXJFAAAAgAgmVvenc57AKLo+oDujTr7DeK97Ff1TfWv8K0yZO0nmQ5QOdJmldLOviXUigl0xdVZj96E0mLcap78uEvBG2oOatWrksYRQ7eU28tnrzcm+0eIflWuUT0VwTFrMS6KBZV9Akhf0HeB28pqDiO7Yb2SHy8zFzt5C7cnHLVzMQTHaRQAAAIACgqpAIf9QRJi/VKZetpJfEwziYNMZNANqL21kYAmqcyc/dHdJATIF3YQIDHeFZAQaAjcwRG8VmGPkuow5VFMiidmIuIlhYLC3gYml6UXtb1n/h+HJ7hXgRG+/0XUfcADnMoJenMxS1Sj3A08kDZpkYYt47/cX9H8tmkKM7MNgGVMACkluc3VyYW5jZTJFAAAAgAgmVvenc57AKLo+oDujTr7DeK97Ff1TfWv8K0yZO0nmQ5QOdJmldLOviXUigl0xdVZj96E0mLcap78uEvBG2oOatWrksYRQ7eU28tnrzcm+0eIflWuUT0VwTFrMS6KBZV9Akhf0HeB28pqDiO7Yb2SHy8zFzt5C7cnHLVzMQTHaRQAAAIAs9pav55hdnl8+8jstqvHgDfaM4qH/nS6w0pnoHGyKaL+ZteCxrCwcb1K5nOf3TGAtOjqi5dMmu2nRltWnJLURR6t07GOOPVIlW+Wf1Wzch2vO10afXHYd7ZpybZ8J/D3Nu4/oPEM/DDsijQ+hi5o4kD6+OoaFz03RzGhdP7GwsQ==\"}");
		System.out.println(p12.getSK());
		p13.setSK(
				"{\"SK\":\"RQAAAIAC117SJj+u6tXFGAzgAq0s0/gsKW9Wp6zeWQtCI2Z0SiGm0gAPdWGsmJZ8Hz4TQ29FvNZZpKK8YoXtIePBBHQ3cxw3/2VPVMj//Qz9LGldPaG2jLXgW7sDsCKlGSyhfYjOO9YQ0KkNB8E3Ir88+/pu6CqSS5QoWasShY8NKLrVFlsAAAADUwAEaWQxM0UAAACAS0aFpRuNWZnmDuFzE2uYIeMnrpNACIklmk6ouDqTVsJv29kyEJeVDc26RGj4SgjpfR1QA4+C2hYZ0521qzDmtkSwHBbUGcP0lz3REJRAY8IOcYK37tTveDZXoshbg8Net4fCZb+KrII05r8EGeL5ik6TFUZ8uHrP2dGiM64s3cBFAAAAgI3E70O333Z2pXxs+OfKm8bxEdrmba5w05ZwglXvV20YS1Y7fwYgDc/5xaaD3hBCRlttNm4Pq2v+gIb/n8pp6UYVf4yxWcklnDrrLElSUWnBQrJUd1nnVQsqHs/Zmd2l+Nz9TCvD5rfmOGUcgD3uGZSUOrUxFZlk7ezpRe1Q8l4WUwAGZG9jdG9yRQAAAIBLRoWlG41ZmeYO4XMTa5gh4yeuk0AIiSWaTqi4OpNWwm/b2TIQl5UNzbpEaPhKCOl9HVADj4LaFhnTnbWrMOa2RLAcFtQZw/SXPdEQlEBjwg5xgrfu1O94NleiyFuDw163h8Jlv4qsgjTmvwQZ4vmKTpMVRny4es/Z0aIzrizdwEUAAACAXf+cGvrv13iCwKAZoc8MVBIeAnG1JkbFDYdTuPxU/gSYlmufDXuM2au1ujuhQpor81iwMi8/9PfxY0c9SN0WoKd3cmP6DM65MTa0EUTzy3bHVX95t2OZPa7nGdCYlYR1HUxybVSi17JzFSiboP4x/i0l5ayq2tsB88kcq/4bEMJTAAlIb3NwaXRhbDFFAAAAgEtGhaUbjVmZ5g7hcxNrmCHjJ66TQAiJJZpOqLg6k1bCb9vZMhCXlQ3NukRo+EoI6X0dUAOPgtoWGdOdtasw5rZEsBwW1BnD9Jc90RCUQGPCDnGCt+7U73g2V6LIW4PDXreHwmW/iqyCNOa/BBni+YpOkxVGfLh6z9nRojOuLN3ARQAAAIACilGSbmYUVD3hd/0FCNNuTgnompiwxO909ZPvwHPdTvRrC/H5Ny/cioIcPpPCspdgMIWkCEvalX92/RB/gFS2VHCDy1I8XpJ3lKITVd96/gip3QZsfYULKjNi47HJYNVoEDvkNra83dm+mrThakmKX1LymYrUge4CbEi0HGyDQQ==\"}");
		System.out.println(p13.getSK());
		p14.setSK(
				"{\"SK\":\"RQAAAICmBKq8PXBMNdE0UJfPEmpw0At2AO+HPvBegLj/lxT/PhVq0kvBovW+/uc1U9cljSdmVXOji0gulpiFgNCiMyjojc6SAvPwYnca2Z3rNnLdHaOItPONVu13vt5rPnqE8FATnWqpTcw6m50FpC5QLHU/y5uvkunCYm6RFEFT/fyrnFsAAAADUwAEaWQxNEUAAACAidpdpctq2n4w8EFOP6kbdRWbbOl2nrEm0bIv2FYmtmGm6Z9kJCcLW+Jqi10EgfyAoj7xEtuI4crCCP038M1ctWNcoRzokqWwLm2qMs7tLcoUIL/E7tQZEfR52rthOFNjzAr+aZQYanSGPinNPXumwepCkFt6wXT4s3xXbKU4LVJFAAAAgEsV9Gq5hRGCSKLF4IYZynPskZs4UjF8VV4a2FQcH3RknNXFyPeqx8gXg301IOQg2OPTF6dwaY2Zj53BUjOpB4wmdL5IURH/HT/AYV+/YipO1c5m6Woo1xPVtcV1arIdFxFJT6bobPtDrTu8Os65Ymvwtsuu6bh20HhMdtL3MJEvUwAGZG9jdG9yRQAAAICJ2l2ly2rafjDwQU4/qRt1FZts6XaesSbRsi/YVia2Yabpn2QkJwtb4mqLXQSB/ICiPvES24jhysII/TfwzVy1Y1yhHOiSpbAubaoyzu0tyhQgv8Tu1BkR9Hnau2E4U2PMCv5plBhqdIY+Kc09e6bB6kKQW3rBdPizfFdspTgtUkUAAACARfwIrg7Awov3OmGzT+vWsA6IB3sXlFYKZMCfcgZUhkCNHZcmoa/CP1W2C4gcwbs2x+cC0Z4zJ26YGmBcTkPnxWQtmwmTQY9l+t7u4b6jrgrxJRaRxItlcRfy0m/zWuqqmdfjUIPFRRHxfOtZI1SYjnSE3+5GA6orWPIpVpuZZE5TAAlIb3NwaXRhbDJFAAAAgInaXaXLatp+MPBBTj+pG3UVm2zpdp6xJtGyL9hWJrZhpumfZCQnC1viaotdBIH8gKI+8RLbiOHKwgj9N/DNXLVjXKEc6JKlsC5tqjLO7S3KFCC/xO7UGRH0edq7YThTY8wK/mmUGGp0hj4pzT17psHqQpBbesF0+LN8V2ylOC1SRQAAAIB0R3cL5DgxKvJy9YyHfpPrzS6nX/C68qQVhBnkrF2/UfTnUbmZ3K1+myn+c/pfNuQwD9T0t/dP3huIVIgAeyh6YytOXy+JITB9NfaEoEfCom5dGB9SDQi9zIyATvOLuKVH0wzdDQvbG5nwWkvlUB8XmElzBTt+v4ZOR5vUesx/9A==\"}");
		System.out.println(p14.getSK());
		p15.setSK(
				"{\"SK\":\"RQAAAIBYZprZJ95ygX9qRVhRYKZ4xXllgvnEqT0adHt51EaKxggM+JKN2QPb0bl0PV57CRPXA6h/5msW2ZK5OPdo9xbIX2ED+aXv3WjBhj/YxVymR1lYEw4czm6zO6/bGjFYfmEFYsUsuxhwYrmkhnkapxEMqiHXfTM4ALzgCXlHUKw8H1sAAAADUwAEaWQxNUUAAACAa8NWwe0KldiSvTkJCCBmrqELMuzYL0fkbjdxZ9gcW+eQrbh0Vaz/Iqy0Q0TvsnnXw7kzQ7xtvo41Q7fZE3aYaxUGBueZ1MOoyy+y2rK790AcU+Ja/7sQpt8KqOYhH6eHmGNfXFGbrX05kGvtAmfbAuyzkCCrXb4rW+V83LWmpk5FAAAAgFUNFhmS9uIN1DvNLaZztDotWaFTRvIgYvDEIylrT9hbo/dEKDL6Y1vqdKTiAoYvf5fTl5+9iUXfime1NT/iGI6IerDn0KFT2ANvawaIgN6Dsr50gQEvXHTlXUyea+Dj+5SzZ1I6ITknxo/SnXkVlUlal/cpqbe2qA0vB0vE5YJeUwAGZG9jdG9yRQAAAIBrw1bB7QqV2JK9OQkIIGauoQsy7NgvR+RuN3Fn2Bxb55CtuHRVrP8irLRDRO+yedfDuTNDvG2+jjVDt9kTdphrFQYG55nUw6jLL7Lasrv3QBxT4lr/uxCm3wqo5iEfp4eYY19cUZutfTmQa+0CZ9sC7LOQIKtdvitb5XzctaamTkUAAACApI5CrSSJsh0W81PDWUWhK6pn15ecUngJQcuB3g/hhc32HMwq+sXcgWsGsM6ibvV7P6eK+uRgEZs26cUVRN+4Gj6pkmDYcWtWykXh7jg1kv8LmkyObLRiKsehhZuaginYRF8D+os5i5hTxaeF2QJj8UBCobxx2Ub1VZ07s7cKDZNTAAlIb3NwaXRhbDNFAAAAgGvDVsHtCpXYkr05CQggZq6hCzLs2C9H5G43cWfYHFvnkK24dFWs/yKstENE77J518O5M0O8bb6ONUO32RN2mGsVBgbnmdTDqMsvstqyu/dAHFPiWv+7EKbfCqjmIR+nh5hjX1xRm619OZBr7QJn2wLss5Agq12+K1vlfNy1pqZORQAAAIAAq5ljQe7yND+nuAzb4QKxMuaDB09ZF1K1G9EhEzh5Q10ZjX5tmr8CRDBUwZl5V+yT6LWktgd41Tvsgr9DHGyPLkCDlkBU14cDdFeBhb0MAEdQqjGiEZr3W9IUEjJ3ebSyJz6pDr7yt0XAmrCSZaw/Y22reVv4zISY3T9d0/Yvwg==\"}");
		System.out.println(p15.getSK());
		p16.setSK(
				"{\"SK\":\"RQAAAICX4zUByRAkubD7QrqgfUu3Eqd/JtG7v2Rfgq+H/C6LqSbsdShCsrhUw5yCzlZaWTXyTw5EC0c3koTqjwNgIuZlVrV/YmH6UQuSkkquvS16y7h7beOyw7CydTgriDnhDJCGlfVAF3nNZG3Eb/VGQHHUThcAiYTNHC1t2TIqHSkH9lsAAAADUwAEaWQxNkUAAACAHP2B2YCZNgo2H1pggjdzI30LtZ4JBcKbEe3/gVHE7GmLt6OlW+dQWwf+Y7pBMUL3TlLfBNgeOf+uFc+OXMMt5DkqLxYGPTBzLuHvCjH1G5bVHvq0xoGhxjxAPZgKxZ83QMQNcg7ApCLI9vPlAvU1nDEweCVg/6He29NWNpkwKvVFAAAAgDwq/21sI9kUWLN1Naf66OxHw0qiDqmlFjefZLxv5cDquQWhNHf82hPvVodlmWHJdKUd8vwTkgnEDgyYosuFDlgDDwQ0V6TYIgmh7eJ9KpL5QkuQdQ4h1lKuNPCZHQn2SQMrPZOVD5HuRg6wIlXCOGwIRZaXZrmm/rGnUT2MfurdUwAFbnVyc2VFAAAAgBz9gdmAmTYKNh9aYII3cyN9C7WeCQXCmxHt/4FRxOxpi7ejpVvnUFsH/mO6QTFC905S3wTYHjn/rhXPjlzDLeQ5Ki8WBj0wcy7h7wox9RuW1R76tMaBocY8QD2YCsWfN0DEDXIOwKQiyPbz5QL1NZwxMHglYP+h3tvTVjaZMCr1RQAAAIBV6md3PytXqCBdyER89yWZUOxJrZ17Ke9O3e4gSwbz9cdQRcPyv7YtMxSoD4TqjHISkspjXxilVlOPCUTF93vye8opKaN9fn75JPJstKRmfxB5ifCx4qrN4OoFJq2LIcEixv8Pc5BnfgrO5Q00vgJmqBUbgLwasREAIqc6p/ArN1MACUhvc3BpdGFsMUUAAACAHP2B2YCZNgo2H1pggjdzI30LtZ4JBcKbEe3/gVHE7GmLt6OlW+dQWwf+Y7pBMUL3TlLfBNgeOf+uFc+OXMMt5DkqLxYGPTBzLuHvCjH1G5bVHvq0xoGhxjxAPZgKxZ83QMQNcg7ApCLI9vPlAvU1nDEweCVg/6He29NWNpkwKvVFAAAAgEm4ft1U+iaFd6ILVx6P84zTD4ZhONyU8wTurTnIURVMpw2/dRaqRurlKHS8NzPJ0crhdoCU+WIh/dQdR6uMQmUJGO+MQ7cMyLQUlHSJEj0pHVyAdtME+TGxPtu919LMxy2OjINGQSuwnUz9Wr2I7dMIKxJTT22ota0cGel7bKGq\"}");
		System.out.println(p16.getSK());
		p17.setSK(
				"{\"SK\":\"RQAAAIChesou78dkwA2JcTnJJ/NX+yq2hZBbC41bcVqUoyCauYqS9WlWSpvCLww8OVTB15cud6IEwX1LhiNRyfK8b7qidJXDJit0z6ltuJ302hvMNsi21IDklY+8Oa38zvoA4dCp5HHd1he05Ias5zqRSWmBahzKP67BtVpDSM6s6aox0VsAAAADUwAEaWQxN0UAAACAYTmefK4ll8mh4s14XMXSD3jzKEvGXjRz7INsEIpZzJ6CG87BTAVF2EcM7Fn303/Y8c3dx7Y571LtLB6HIDsJIzbACajClIJbpsne8orcYRbxg+lzquC84e60aDGcSNkJ+BlQNyUFDeW11bHhskss2SXgqNwqh3/il5J4JWt1iw1FAAAAgI+PMLoD74JQpfQf0iAMcPkayrdqo8g80+RkuC3/lXGbc+WfGq4almBHY/yTW6YYfNvP9H3NnHIUqCUkSKSrT1NZ09c47afX5G30RH7lyWdi2d8OcGw4ngOEIuYaku8eFjd/SP/Q9JX1sJY+E+w4sAYHExUMIzqyJde/cebrEzlEUwAFbnVyc2VFAAAAgGE5nnyuJZfJoeLNeFzF0g948yhLxl40c+yDbBCKWcyeghvOwUwFRdhHDOxZ99N/2PHN3ce2Oe9S7SwehyA7CSM2wAmowpSCW6bJ3vKK3GEW8YPpc6rgvOHutGgxnEjZCfgZUDclBQ3ltdWx4bJLLNkl4KjcKod/4peSeCVrdYsNRQAAAICB1GEWfRwWYD1UfpR6bw/K0QDepw1M/N5I4cMZz/ouUG9a+kUsix6qvhnefoZrvLUdWLY1l4ATsr9jrtqhwz4hCQNMmjX47qr4JnP7TUMQk0BGbsIa13ive7lI3/lUkD+T+ATDCZQcU8KP7/tRK/XQakD9LgpICNTMTXMHNRcfT1MACUhvc3BpdGFsMkUAAACAYTmefK4ll8mh4s14XMXSD3jzKEvGXjRz7INsEIpZzJ6CG87BTAVF2EcM7Fn303/Y8c3dx7Y571LtLB6HIDsJIzbACajClIJbpsne8orcYRbxg+lzquC84e60aDGcSNkJ+BlQNyUFDeW11bHhskss2SXgqNwqh3/il5J4JWt1iw1FAAAAgAy+yXrpgRV8zCHw0KFCemjHmQuus7+dqZxAgKHIXPz6R1SCEWAzfe+AS9KZqs9tF8vvLA59HNdrPFKHJu47R36C72N+auEUWr6YG40/b69YSZbPGwUJDJbYoNIVgkqhohqx3VTs+T+aNw2mq7Zsoi+NdxIfTVrQNyGxcQ/D08eH\"}");
		System.out.println(p17.getSK());
		p18.setSK(
				"{\"SK\":\"RQAAAICMir3hoobyNUbNQWzSzfwD3++dUJoJuWwaotPfTVvHVCx7OCVQpJGV1Ckm6RvOIsrLrMFjVBzblVh7aPRmOHfTXgkj9xTlXCVmqdNLQPCYsYZQpG/H2ymtr2zK0/QXS187K+a8OOrUSRS99lgFE7MJo3U2dBDsky1VBUGD6381DVsAAAADUwAEaWQxOEUAAACAAS4U5ooHsz9rW58alZI4mH3hkNSDSwKaKzelbgk9xjX8kX9eFNXpzymgp5GLh1kPx/QXaRhhjSd3gVz1ng6tX22LDwz9ZDLqTOLucE3ovxHJUoak67QXBSyEKOdaQzkwdyQmA4QXY2K9QcbVnQt0qs0SeWzu90p19++426vRBnhFAAAAgIX4KJ3vzywPKWuuRDSY9n5YSnak2dFnHuUf1pMCknGnYkOG2XFPNmTzcbhuJ08NMZsTj/yBwp4UE5aKfrojAzFr4zacZcqv96uhKymjt5RixldfUb4Gh49FqI7naX/bx+Xulrf+J9erYILNx+4vYjKqGLQyS3XSO6IpapLiWi5UUwAFbnVyc2VFAAAAgAEuFOaKB7M/a1ufGpWSOJh94ZDUg0sCmis3pW4JPcY1/JF/XhTV6c8poKeRi4dZD8f0F2kYYY0nd4Fc9Z4OrV9tiw8M/WQy6kzi7nBN6L8RyVKGpOu0FwUshCjnWkM5MHckJgOEF2NivUHG1Z0LdKrNEnls7vdKdffvuNur0QZ4RQAAAIAxx07/N9mGGWhnWIaJaKgl7wNI+akYDzaL4KUl4B8NXdHhteIX9ZxXSoWL1kWRAs/nNoEssSbO0HYn5bgoT3lHW8C7OjF1fWXOhdAyv7ikleC59ZdxBaqfGpCJDgVOkJgtLFK1pGooX0Sl0ftM4iHNFeu+a8jbuqA4dVQ1NymXt1MACUhvc3BpdGFsM0UAAACAAS4U5ooHsz9rW58alZI4mH3hkNSDSwKaKzelbgk9xjX8kX9eFNXpzymgp5GLh1kPx/QXaRhhjSd3gVz1ng6tX22LDwz9ZDLqTOLucE3ovxHJUoak67QXBSyEKOdaQzkwdyQmA4QXY2K9QcbVnQt0qs0SeWzu90p19++426vRBnhFAAAAgGU1k9YPgjOcsV99Oe8XIRvSK20fCeU9vk2UFD/bo1lEL/uvM8BpJlBWI7rrBvGlSAKvzqiH8gP2rOl4kcFmAnZtR9uJ4vsHrgFUatY8ov2TtwIWMu6nwmFa+7H46d92g7Uh7bHKSBJ8gHMZGy6/f1apLQPernPw/KyQ8Vmx6O/Z\"}");
		System.out.println(p18.getSK());
		
		/*
		 * p1.policies.setBIReadingPolicy("id1");
		 * p2.policies.setBIReadingPolicy("id2");
		 * p3.policies.setBIReadingPolicy("id3");
		 * p4.policies.setBIReadingPolicy("id4");
		 * p5.policies.setBIReadingPolicy("id5");
		 * p6.policies.setBIReadingPolicy("id6");
		 * p7.policies.setBIReadingPolicy("id7");
		 * p8.policies.setBIReadingPolicy("id8");
		 * p9.policies.setBIReadingPolicy("id9");
		 * p10.policies.setBIReadingPolicy("id10");
		 * p11.policies.setBIReadingPolicy("id11");
		 * p12.policies.setBIReadingPolicy("id12");
		 * p13.policies.setBIReadingPolicy("id13");
		 * p14.policies.setBIReadingPolicy("id14");
		 * p15.policies.setBIReadingPolicy("id15");
		 * p16.policies.setBIReadingPolicy("id16");
		 * p17.policies.setBIReadingPolicy("id17");
		 * p18.policies.setBIReadingPolicy("id18");
		 * 
		 * p1.insertBasicInfoDB("Name1", "1992/05/06", "Tours_FR", "Female",
		 * "French", "A str 2, Enschede", "003363215648");
		 * p2.insertBasicInfoDB("Name2", "1975/04/30", "Blois_FR", "Male",
		 * "French", "B str 5, Enschede", "003361548925");
		 * p3.insertBasicInfoDB("Name3", "1985/12/24", "Lignieres_FR", "Female",
		 * "French", "C str 1, Enschede", "0033754136859");
		 * p4.insertBasicInfoDB("Name4", "1979/08/31", "Timisoara_RO", "Male",
		 * "Romanian", "D str 9, Enschede", "003156954625");
		 * p5.insertBasicInfoDB("Name5", "1999/04/15", "Arad_RO", "Female",
		 * "Romanian", "E str 10, Enschede", "005415468295");
		 * p6.insertBasicInfoDB("Name6", "1994/03/19", "Dublin_IE", "Male",
		 * "Irish", "F str 65, Enschede", "0031569842");
		 * p7.insertBasicInfoDB("Name7", "1989/03/01", "Belfast_UK", "Female",
		 * "Dutch", "G str 15, Enschede", "0048615973325");
		 * p8.insertBasicInfoDB("Name8", "1979/06/29", "London_UK", "Male",
		 * "French", "H str 86, Enschede", "00154685265");
		 * p9.insertBasicInfoDB("Name9", "1983/12/12", "Valencia_SP", "Female",
		 * "Spanish", "I str 2, Enschede", "001549535452");
		 * p10.insertBasicInfoDB("Name10", "1985/11/16", "Enschede_NL", "Male",
		 * "Dutch", "J str 64, Enschede", "0031578952146");
		 * p11.insertBasicInfoDB("Name11", "1991/12/19", "Utrecht_NL", "Female",
		 * "Dutch", "K str 95, Enschede", "001349824556");
		 * p12.insertBasicInfoDB("Name12", "1975/04/30", "Trento_IT", "Male",
		 * "Italian", "L str 2, Enschede", "00124588635");
		 * p13.insertBasicInfoDB("Name13", "1985/12/24", "Berlin_D", "Female",
		 * "German", "M str 4, Enschede", "001548214589982");
		 * p14.insertBasicInfoDB("Name14", "1979/08/31", "Barcelona_SP", "Male",
		 * "Dutch", "N str 6, Enschede", "00135424896248");
		 * p15.insertBasicInfoDB("Name15", "1979/06/29", "Amsterdam_NL",
		 * "Female", "Dutch", "O str 54, Enschede", "004521688524");
		 * p16.insertBasicInfoDB("Name16", "1983/12/12", "LosAngeles_US",
		 * "Male", "Dutch", "P str 98, Enschede", "00457954599");
		 * p17.insertBasicInfoDB("Name17", "1985/11/16", "Genova_IT", "Female",
		 * "Italian", "Q str 3, Enschede", "001751465588");
		 * p18.insertBasicInfoDB("Name18", "1979/08/31", "Rome_IT", "Male",
		 * "Italian", "R str 2, Enschede", "0016885547995");
		 */
		/*
		 * p1.policies.setBHIReadingPolicy("id1");
		 * p2.policies.setBHIReadingPolicy("id2");
		 * p3.policies.setBHIReadingPolicy("id3");
		 * p4.policies.setBHIReadingPolicy("id4");
		 * p5.policies.setBHIReadingPolicy("id5");
		 * p6.policies.setBHIReadingPolicy("id6");
		 * p7.policies.setBHIReadingPolicy("id7");
		 * p8.policies.setBHIReadingPolicy("id8");
		 * p9.policies.setBHIReadingPolicy("id9");
		 * p10.policies.setBHIReadingPolicy("id10");
		 * p11.policies.setBHIReadingPolicy("id11");
		 * p12.policies.setBHIReadingPolicy("id12");
		 * p13.policies.setBHIReadingPolicy("id13");
		 * p14.policies.setBHIReadingPolicy("id14");
		 * p15.policies.setBHIReadingPolicy("id15");
		 * p16.policies.setBHIReadingPolicy("id16");
		 * p17.policies.setBHIReadingPolicy("id17");
		 * p18.policies.setBHIReadingPolicy("id18");
		 * 
		 * p1.insertBasicHealthInfoDB(1,"O+","65","173","0031245685",13);
		 * p2.insertBasicHealthInfoDB(2,"A+","68","179","0031599645254",14);
		 * p3.insertBasicHealthInfoDB(3,"B+","75","175","00759648213",15);
		 * p4.insertBasicHealthInfoDB(4,"AB+","83","183","00154632879",13);
		 * p5.insertBasicHealthInfoDB(5,"O-","75","179","00854697213",14);
		 * p6.insertBasicHealthInfoDB(6,"A-","69","150","00164527893",15);
		 * p7.insertBasicHealthInfoDB(7,"B-","73","159","00852164973",13);
		 * p8.insertBasicHealthInfoDB(8,"AB-","71","171","00648521397",14);
		 * p9.insertBasicHealthInfoDB(9,"A+","93","180","00789654321",15);
		 * p10.insertBasicHealthInfoDB(10,"B+","89","186","00152483679",13);
		 * p11.insertBasicHealthInfoDB(11,"A+","85","175","00921365478",14);
		 * p12.insertBasicHealthInfoDB(12,"B+","94","200","00859631427",15);
		 * p13.insertBasicHealthInfoDB(13,"AB+","75","185","00721698534",14);
		 * p14.insertBasicHealthInfoDB(14,"O+","80","190","00621853479",15);
		 * p15.insertBasicHealthInfoDB(15,"A-","85","192","00648539217",13);
		 * p16.insertBasicHealthInfoDB(16,"B-","59","165","00521698347",13);
		 * p17.insertBasicHealthInfoDB(17,"A-","64","174","00126547893",14);
		 * p18.insertBasicHealthInfoDB(18,"A+","60","175","00850164392",15);
		 */
		/*
		 * p1.addHealthClubVisitsDB(1,1,"2018/10/31","1 h","cardio","good","");
		 * p2.addHealthClubVisitsDB(2,2,"2018/10/31","1 h","cardio","good","");
		 * p3.addHealthClubVisitsDB(3,3,"2018/11/01","1 h 30 m","swimming",
		 * "good", ""); p4.addHealthClubVisitsDB(4,4,"2018/11/01","1 h"
		 * ,"treadmill","very good" ,"");
		 * p5.addHealthClubVisitsDB(5,5,"2018/11/02","45 m","weight lifting"
		 * ,"bad","hurt themselves during class");
		 * p13.addHealthClubVisitsDB(13,13,"2018/11/02","1 h","squash","none",""
		 * );
		 * p14.addHealthClubVisitsDB(14,14,"2018/11/02","2 h","cycling","good",
		 * "");
		 * p15.addHealthClubVisitsDB(15,15,"2018/11/03","1 h","aerobics","good",
		 * "") ;
		 * p16.addHealthClubVisitsDB(16,16,"2018/11/03","1 h","sauna","good","")
		 * ; p17.addHealthClubVisitsDB(17,17,"2018/11/04","1 h 45 m","boxing",
		 * "bad","hurt themselves during class");
		 * p18.addHealthClubVisitsDB(18,18,"2018/11/05","1 h","cycling","none",
		 * "");
		 * p1.addHealthClubVisitsDB(1,1,"2018/11/06","2 h","cardio","none","");
		 * p2.addHealthClubVisitsDB(2,2,"2018/11/08","2 h","weight lifting"
		 * ,"bad","hurt themselves during class");
		 * p3.addHealthClubVisitsDB(3,3,"2018/11/08","2 h","boxing","good","");
		 * p4.addHealthClubVisitsDB(4,4,"2018/11/08","1 h 45 m","swimming",
		 * "good", "");
		 * p5.addHealthClubVisitsDB(5,5,"2018/11/08","2 h","treadmills","good",
		 * "") ;
		 */
		/*
		 * p1.policies.
		 * setMVReadingPolicy("id1 OR Hospital1 OR Hospital3 OR ( doctor AND ( id13 OR id15 ) ) OR Insurance6"
		 * ); p2.policies.
		 * setMVReadingPolicy("id2 OR Hospital2 OR Hospital1 OR ( doctor AND ( id15 OR id14 ) )"
		 * ); p3.policies.
		 * setMVReadingPolicy("id3 OR Hospital2 OR Hospital3 OR ( doctor AND ( id15 OR id13 ) )"
		 * ); p4.policies.
		 * setMVReadingPolicy("id4 OR Hospital2 OR ( doctor AND ( id15 OR id13 ) )"
		 * ); p5.policies.
		 * setMVReadingPolicy("id5 OR Hospital1 OR ( doctor AND ( id15 OR id14 ) )"
		 * ); p6.policies.
		 * setMVReadingPolicy("id6 OR Hospital2 OR ( doctor AND id15 )");
		 * 
		 * p1.addMedicalVisitDB(1,"2018/10/31","2018/10/31",
		 * "Allergies","allergic reaction to peanut",1);
		 * p2.addMedicalVisitDB(2,"2018/10/31","2018/10/31",
		 * "Anxiety","has seen a psychatrist",2);
		 * p3.addMedicalVisitDB(3,"2018/10/31","2018/11/01","Asthma","-",3);
		 * p4.addMedicalVisitDB(4,"2018/11/01","2018/11/01","Atopic eczema"
		 * ,"prescribed medicine",4);
		 * p5.addMedicalVisitDB(5,"2018/11/01","2018/11/05",
		 * "Appendicitis","surgery performed",5);
		 * p6.addMedicalVisitDB(6,"2018/11/02","2018/11/08",
		 * "Psoriasis","prescribed medicine",4);
		 * p1.addMedicalVisitDB(1,"2018/11/02","2018/11/02","Chest pain"
		 * ,"false alarm",3);
		 * p2.addMedicalVisitDB(2,"2018/11/02","2018/11/02","Common cold"
		 * ,"need rest, prescribed medicine",2);
		 * p3.addMedicalVisitDB(3,"2018/11/03","2018/11/03",
		 * "Influenza","need rest, prescribed medicine",1);
		 * p2.addMedicalVisitDB(2,"2018/11/03","2018/11/09","Meningitis",
		 * "antibiotic",5); p1.addMedicalVisitDB(1,"2018/11/05","2018/11/05",
		 * "Lupus","starting; prescribed medicine",1);
		 */
		/*
		 * p1.addMedicineDB(1,"epinephrine", "when needed", "-", "-", 1);
		 * p3.addMedicineDB(3,"Ventolin HFA", "when needed", "-", "-", 3);
		 * p4.addMedicineDB(4,"Cream", "morning and night", "2018/11/01",
		 * "2018/12/01", 4); p6.addMedicineDB(6,"Cream", "morning and night",
		 * "2018/11/01", "2018/12/01", 6); p2.addMedicineDB(2,"Aspirin",
		 * "max 4 a day", "2018/11/02", "2018/11/05", 8);
		 * p3.addMedicineDB(3,"Ibuprofen", "max 3 a day", "2018/11/03",
		 * "2018/11/08", 9); p2.addMedicineDB(2,"antibiotic", "every 12 hours",
		 * "2018/11/03", "2018/11/10", 10);
		 * p1.addMedicineDB(1,"Corticosteroids", "1 mg/kg/day", "2018/11/05",
		 * "2018/11/15", 11);
		 */

		// System.out.println(p2.readMedicineDB(1));

		// p1.setSK(SKJSONString);
		// String SKJSONString = server.generateSecretKey(p1.getAttrs());
		// System.out.println(SKJSONString);
		// p1.setSK("{\"SK\":\"RQAAAIA4dsg5bZFfmhivOeGwdvoUrLfFTCUy83iXxJ0sPblxaRCrFhHsBe5mwZCEytlGE6xj7OjztN17yha4M+GUxT3/andNtMj8qWBKJdIgJvHQONbBYjYPjoJVjg6BZNoDCKQ11CD5yD3iulJ9fX42/4UTxLVBYuG7rtfj7XYLcmfS6lsAAAADUwADaWQyRQAAAIAjRQx+/jmTh6KZhOclyHbVcs6viB/hpl25w+DsjSKj9ZfDxmYyWSlgvKQHVipxQai60Gdrx0psPRXUTP4BYnhsDlotX86ifu9tGKZJobri9FsamNw9sDA3AFNSfi08zygKWIAIYGlUU2AEoRK2U5pdQNx643RK1Ta8y42A4l6ES0UAAACAdxaasAvvdL08qoNaKLE/ehVrItQxBSZ4N6mFTvdGigV/VPeLe3YOC4QcgFUaV384uNVfF2zijrubhx9dP5/6bHakzQKE2oKznmcTVKK1e11kK0Z6ao7NHBwyk8WNQA/+tZ6cDXGGccFfB90smOYSHHdOnABL4W6/mgBy79ns7cpTAAhlbXBsb3llZUUAAACAI0UMfv45k4eimYTnJch21XLOr4gf4aZducPg7I0io/WXw8ZmMlkpYLykB1YqcUGoutBna8dKbD0V1Ez+AWJ4bA5aLV/Oon7vbRimSaG64vRbGpjcPbAwNwBTUn4tPM8oCliACGBpVFNgBKEStlOaXUDceuN0StU2vMuNgOJehEtFAAAAgFiSXU+gnbGGxp6yL/r+6WxiC7yen4FBENOv3z242KzToRobndnxYUUICtUXeIxyNR5kqNZJt7cgA0VFWf8JApppAMKWHnP1CGXGUF5Dggrw95xDJ6EXxpyTaHBppn8kNdBGSoqOItviT4v4TPBUNRNdU7WSD9ypaG1RstqoYdqtUwAIY29tcGFueTFFAAAAgCNFDH7+OZOHopmE5yXIdtVyzq+IH+GmXbnD4OyNIqP1l8PGZjJZKWC8pAdWKnFBqLrQZ2vHSmw9FdRM/gFieGwOWi1fzqJ+720YpkmhuuL0WxqY3D2wMDcAU1J+LTzPKApYgAhgaVRTYAShErZTml1A3HrjdErVNrzLjYDiXoRLRQAAAIB210hH2yKd6ouGfsuQ+lVKskK1A8Z3qkumFGz3LwFfIKUoJzDqvOFX4BIMXbHGlWaJPxSrxbTssyJp30YXpogZHdQq/+2Izd7E9Qki+CWV+YrAJaGRvcVeFhijDu/5nHyDPQgOB+sS4Oapy6zAyughUHDe1iLejLC+Jd7HCkfCkw==\"}");

		// p1.updateBasicInfoDB(2, "Name", "Birth date", "Birth place",
		// "Gender","Nationality", "Address","number");
		// ArrayList<String> bi = p1.readBasicInfoDB(2);
		// System.out.println(bi.toString());
		// System.out.println(p1.getAttrs()[0]+p1.getAttrs()[1]+p1.getAttrs()[2]);
		// p1.getAttributeListDB();
		// System.out.println(p1.getAttrs()[0]+p1.getAttrs()[1]+p1.getAttrs()[2]);
		/*
		 * DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p1.getId() + ",'" + p1.policies.getBIWritingPolicy() + "','" +
		 * p1.policies.getBIReadingPolicy() + "','" +
		 * p1.policies.getBHIWritingPolicy() + "','" +
		 * p1.policies.getBHIReadingPolicy() + "','" +
		 * p1.policies.getMVWritingPolicy() + "','" +
		 * p1.policies.getMVReadingPolicy() + "','" +
		 * p1.policies.getMWritingPolicy() + "','" +
		 * p1.policies.getMReadingPolicy() + "','" +
		 * p1.policies.getHCVWritingPolicy() + "','" +
		 * p1.policies.getHCVReadingPolicy() + "')");
		 * 
		 * DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p2.getId() + ",'" + p2.policies.getBIWritingPolicy() + "','" +
		 * p2.policies.getBIReadingPolicy() + "','" +
		 * p2.policies.getBHIWritingPolicy() + "','" +
		 * p2.policies.getBHIReadingPolicy() + "','" +
		 * p2.policies.getMVWritingPolicy() + "','" +
		 * p2.policies.getMVReadingPolicy() + "','" +
		 * p2.policies.getMWritingPolicy() + "','" +
		 * p2.policies.getMReadingPolicy() + "','" +
		 * p2.policies.getHCVWritingPolicy() + "','" +
		 * p2.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p3.getId() + ",'" + p3.policies.getBIWritingPolicy() + "','" +
		 * p3.policies.getBIReadingPolicy() + "','" +
		 * p3.policies.getBHIWritingPolicy() + "','" +
		 * p3.policies.getBHIReadingPolicy() + "','" +
		 * p3.policies.getMVWritingPolicy() + "','" +
		 * p3.policies.getMVReadingPolicy() + "','" +
		 * p3.policies.getMWritingPolicy() + "','" +
		 * p3.policies.getMReadingPolicy() + "','" +
		 * p3.policies.getHCVWritingPolicy() + "','" +
		 * p3.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p4.getId() + ",'" + p4.policies.getBIWritingPolicy() + "','" +
		 * p4.policies.getBIReadingPolicy() + "','" +
		 * p4.policies.getBHIWritingPolicy() + "','" +
		 * p4.policies.getBHIReadingPolicy() + "','" +
		 * p4.policies.getMVWritingPolicy() + "','" +
		 * p4.policies.getMVReadingPolicy() + "','" +
		 * p4.policies.getMWritingPolicy() + "','" +
		 * p4.policies.getMReadingPolicy() + "','" +
		 * p4.policies.getHCVWritingPolicy() + "','" +
		 * p4.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p5.getId() + ",'" + p5.policies.getBIWritingPolicy() + "','" +
		 * p5.policies.getBIReadingPolicy() + "','" +
		 * p5.policies.getBHIWritingPolicy() + "','" +
		 * p5.policies.getBHIReadingPolicy() + "','" +
		 * p5.policies.getMVWritingPolicy() + "','" +
		 * p5.policies.getMVReadingPolicy() + "','" +
		 * p5.policies.getMWritingPolicy() + "','" +
		 * p5.policies.getMReadingPolicy() + "','" +
		 * p5.policies.getHCVWritingPolicy() + "','" +
		 * p5.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p6.getId() + ",'" + p6.policies.getBIWritingPolicy() + "','" +
		 * p6.policies.getBIReadingPolicy() + "','" +
		 * p6.policies.getBHIWritingPolicy() + "','" +
		 * p6.policies.getBHIReadingPolicy() + "','" +
		 * p6.policies.getMVWritingPolicy() + "','" +
		 * p6.policies.getMVReadingPolicy() + "','" +
		 * p6.policies.getMWritingPolicy() + "','" +
		 * p6.policies.getMReadingPolicy() + "','" +
		 * p6.policies.getHCVWritingPolicy() + "','" +
		 * p6.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p7.getId() + ",'" + p7.policies.getBIWritingPolicy() + "','" +
		 * p7.policies.getBIReadingPolicy() + "','" +
		 * p7.policies.getBHIWritingPolicy() + "','" +
		 * p7.policies.getBHIReadingPolicy() + "','" +
		 * p7.policies.getMVWritingPolicy() + "','" +
		 * p7.policies.getMVReadingPolicy() + "','" +
		 * p7.policies.getMWritingPolicy() + "','" +
		 * p7.policies.getMReadingPolicy() + "','" +
		 * p7.policies.getHCVWritingPolicy() + "','" +
		 * p7.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p8.getId() + ",'" + p8.policies.getBIWritingPolicy() + "','" +
		 * p8.policies.getBIReadingPolicy() + "','" +
		 * p8.policies.getBHIWritingPolicy() + "','" +
		 * p8.policies.getBHIReadingPolicy() + "','" +
		 * p8.policies.getMVWritingPolicy() + "','" +
		 * p8.policies.getMVReadingPolicy() + "','" +
		 * p8.policies.getMWritingPolicy() + "','" +
		 * p8.policies.getMReadingPolicy() + "','" +
		 * p8.policies.getHCVWritingPolicy() + "','" +
		 * p8.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p9.getId() + ",'" + p9.policies.getBIWritingPolicy() + "','" +
		 * p9.policies.getBIReadingPolicy() + "','" +
		 * p9.policies.getBHIWritingPolicy() + "','" +
		 * p9.policies.getBHIReadingPolicy() + "','" +
		 * p9.policies.getMVWritingPolicy() + "','" +
		 * p9.policies.getMVReadingPolicy() + "','" +
		 * p9.policies.getMWritingPolicy() + "','" +
		 * p9.policies.getMReadingPolicy() + "','" +
		 * p9.policies.getHCVWritingPolicy() + "','" +
		 * p9.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p10.getId() + ",'" + p10.policies.getBIWritingPolicy() + "','" +
		 * p10.policies.getBIReadingPolicy() + "','" +
		 * p10.policies.getBHIWritingPolicy() + "','" +
		 * p10.policies.getBHIReadingPolicy() + "','" +
		 * p10.policies.getMVWritingPolicy() + "','" +
		 * p10.policies.getMVReadingPolicy() + "','" +
		 * p10.policies.getMWritingPolicy() + "','" +
		 * p10.policies.getMReadingPolicy() + "','" +
		 * p10.policies.getHCVWritingPolicy() + "','" +
		 * p10.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p11.getId() + ",'" + p11.policies.getBIWritingPolicy() + "','" +
		 * p11.policies.getBIReadingPolicy() + "','" +
		 * p11.policies.getBHIWritingPolicy() + "','" +
		 * p11.policies.getBHIReadingPolicy() + "','" +
		 * p11.policies.getMVWritingPolicy() + "','" +
		 * p11.policies.getMVReadingPolicy() + "','" +
		 * p11.policies.getMWritingPolicy() + "','" +
		 * p11.policies.getMReadingPolicy() + "','" +
		 * p11.policies.getHCVWritingPolicy() + "','" +
		 * p11.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p12.getId() + ",'" + p12.policies.getBIWritingPolicy() + "','" +
		 * p12.policies.getBIReadingPolicy() + "','" +
		 * p12.policies.getBHIWritingPolicy() + "','" +
		 * p12.policies.getBHIReadingPolicy() + "','" +
		 * p12.policies.getMVWritingPolicy() + "','" +
		 * p12.policies.getMVReadingPolicy() + "','" +
		 * p12.policies.getMWritingPolicy() + "','" +
		 * p12.policies.getMReadingPolicy() + "','" +
		 * p12.policies.getHCVWritingPolicy() + "','" +
		 * p12.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p13.getId() + ",'" + p13.policies.getBIWritingPolicy() + "','" +
		 * p13.policies.getBIReadingPolicy() + "','" +
		 * p13.policies.getBHIWritingPolicy() + "','" +
		 * p13.policies.getBHIReadingPolicy() + "','" +
		 * p13.policies.getMVWritingPolicy() + "','" +
		 * p13.policies.getMVReadingPolicy() + "','" +
		 * p13.policies.getMWritingPolicy() + "','" +
		 * p13.policies.getMReadingPolicy() + "','" +
		 * p13.policies.getHCVWritingPolicy() + "','" +
		 * p13.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p14.getId() + ",'" + p14.policies.getBIWritingPolicy() + "','" +
		 * p14.policies.getBIReadingPolicy() + "','" +
		 * p14.policies.getBHIWritingPolicy() + "','" +
		 * p14.policies.getBHIReadingPolicy() + "','" +
		 * p14.policies.getMVWritingPolicy() + "','" +
		 * p14.policies.getMVReadingPolicy() + "','" +
		 * p14.policies.getMWritingPolicy() + "','" +
		 * p14.policies.getMReadingPolicy() + "','" +
		 * p14.policies.getHCVWritingPolicy() + "','" +
		 * p14.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p15.getId() + ",'" + p15.policies.getBIWritingPolicy() + "','" +
		 * p15.policies.getBIReadingPolicy() + "','" +
		 * p15.policies.getBHIWritingPolicy() + "','" +
		 * p15.policies.getBHIReadingPolicy() + "','" +
		 * p15.policies.getMVWritingPolicy() + "','" +
		 * p15.policies.getMVReadingPolicy() + "','" +
		 * p15.policies.getMWritingPolicy() + "','" +
		 * p15.policies.getMReadingPolicy() + "','" +
		 * p15.policies.getHCVWritingPolicy() + "','" +
		 * p15.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p16.getId() + ",'" + p16.policies.getBIWritingPolicy() + "','" +
		 * p16.policies.getBIReadingPolicy() + "','" +
		 * p16.policies.getBHIWritingPolicy() + "','" +
		 * p16.policies.getBHIReadingPolicy() + "','" +
		 * p16.policies.getMVWritingPolicy() + "','" +
		 * p16.policies.getMVReadingPolicy() + "','" +
		 * p16.policies.getMWritingPolicy() + "','" +
		 * p16.policies.getMReadingPolicy() + "','" +
		 * p16.policies.getHCVWritingPolicy() + "','" +
		 * p16.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p17.getId() + ",'" + p17.policies.getBIWritingPolicy() + "','" +
		 * p17.policies.getBIReadingPolicy() + "','" +
		 * p17.policies.getBHIWritingPolicy() + "','" +
		 * p17.policies.getBHIReadingPolicy() + "','" +
		 * p17.policies.getMVWritingPolicy() + "','" +
		 * p17.policies.getMVReadingPolicy() + "','" +
		 * p17.policies.getMWritingPolicy() + "','" +
		 * p17.policies.getMReadingPolicy() + "','" +
		 * p17.policies.getHCVWritingPolicy() + "','" +
		 * p17.policies.getHCVReadingPolicy() + "')"); DBConnection
		 * .update("INSERT INTO sdmproject.policies (id_person, BIWritingPolicy, BIReadingPolicy, BHIWritingPolicy, BHIReadingPolicy, MVWritingPolicy, MVReadingPolicy, MWritingPolicy, MReadingPolicy, HCVWritingPolicy, HCVReadingPolicy) VALUES ("
		 * + p18.getId() + ",'" + p18.policies.getBIWritingPolicy() + "','" +
		 * p18.policies.getBIReadingPolicy() + "','" +
		 * p18.policies.getBHIWritingPolicy() + "','" +
		 * p18.policies.getBHIReadingPolicy() + "','" +
		 * p18.policies.getMVWritingPolicy() + "','" +
		 * p18.policies.getMVReadingPolicy() + "','" +
		 * p18.policies.getMWritingPolicy() + "','" +
		 * p18.policies.getMReadingPolicy() + "','" +
		 * p18.policies.getHCVWritingPolicy() + "','" +
		 * p18.policies.getHCVReadingPolicy() + "')");
		 */

		
		/*p1.updateBasicInfoDB(1,"Name1", "1992/05/06", "Tours_FR", "Female","French", "A str 2, Enschede", "003363215648");
		p2.updateBasicInfoDB(2,"Name2", "1975/04/30", "Blois_FR", "Male","French", "B str 5, Enschede", "003361548925");
		p3.updateBasicInfoDB(3,"Name3", "1985/12/24", "Lignieres_FR", "Female","French", "C str 1, Enschede", "0033754136859");
		p4.updateBasicInfoDB(4,"Name4", "1979/08/31", "Timisoara_RO", "Male","Romanian", "D str 9, Enschede", "003156954625");
		p5.updateBasicInfoDB(5,"Name5", "1999/04/15", "Arad_RO", "Female","Romanian", "E str 10, Enschede", "005415468295");
		p6.updateBasicInfoDB(6,"Name6", "1994/03/19", "Dublin_IE", "Male","Irish", "F str 65, Enschede", "0031569842");
		p7.updateBasicInfoDB(7,"Name7", "1989/03/01", "Belfast_UK", "Female","Dutch", "G str 15, Enschede", "0048615973325");
		p8.updateBasicInfoDB(8,"Name8", "1979/06/29", "London_UK", "Male","French", "H str 86, Enschede", "00154685265");
		p9.updateBasicInfoDB(9,"Name9", "1983/12/12", "Valencia_SP", "Female","Spanish", "I str 2, Enschede", "001549535452");
		p10.updateBasicInfoDB(10,"Name10", "1985/11/16", "Enschede_NL", "Male","Dutch", "J str 64, Enschede", "0031578952146");
		p11.updateBasicInfoDB(11,"Name11", "1991/12/19", "Utrecht_NL", "Female","Dutch", "K str 95, Enschede", "001349824556");
		p12.updateBasicInfoDB(12,"Name12", "1975/04/30", "Trento_IT", "Male","Italian", "L str 2, Enschede", "00124588635");
		p13.updateBasicInfoDB(13,"Name13", "1985/12/24", "Berlin_D", "Female","German", "M str 4, Enschede", "001548214589982");
		p14.updateBasicInfoDB(14,"Name14", "1979/08/31", "Barcelona_SP", "Male","Dutch", "N str 6, Enschede", "00135424896248");
		p15.updateBasicInfoDB(15,"Name15", "1979/06/29", "Amsterdam_NL","Female", "Dutch", "O str 54, Enschede", "004521688524");
		p16.updateBasicInfoDB(16,"Name16", "1983/12/12", "LosAngeles_US","Male", "Dutch", "P str 98, Enschede", "00457954599");
		p17.updateBasicInfoDB(17,"Name17", "1985/11/16", "Genova_IT", "Female","Italian", "Q str 3, Enschede", "001751465588");
		p18.updateBasicInfoDB(18,"Name18", "1979/08/31", "Rome_IT", "Male","Italian", "R str 2, Enschede", "0016885547995");*/
		
	}

}