/*

    gl-core  Core interfaces and classes for the gl project.
    Copyright (c) 2012-2015 National Marrow Donor Program (NMDP)

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 3 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.fsf.org/licensing/licenses/lgpl.html
    > http://www.opensource.org/licenses/lgpl-license.php

*/
package org.nmdp.gl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Allele.
 */
public final class AlleleTest {
    private final Locus locus = new Locus("http://immunogenomics.org/locus/0", "HLA-A");

    @Test(expected=NullPointerException.class)
    public void testConstructorNullIdentifier() {
        new Allele(null, "A01234", "HLA-A*01:01:01:01", locus);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullAccession() {
        new Allele("http://immunogenomics.org/allele/0", null, "HLA-A*01:01:01:01", locus);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullGlstring() {
        new Allele("http://immunogenomics.org/allele/0", "A01234", null, locus);
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullLocus() {
        new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", null);
    }

    @Test
    public void testId() {
        Allele allele = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        assertEquals("http://immunogenomics.org/allele/0", allele.getId());
    }

    @Test
    public void testAccession() {
        Allele allele = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        assertEquals("A01234", allele.getAccession());
    }

    @Test
    public void testGlstring() {
        Allele allele = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        assertEquals("HLA-A*01:01:01:01", allele.getGlstring());
    }

    @Test
    public void testLocus() {
        Allele allele = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        assertEquals(locus, allele.getLocus());
    }

    @Test
    public void testHashCode() {
        Allele a = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        Allele sameA = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);

        assertEquals(a.hashCode(), sameA.hashCode());
    }

    @Test
    public void testEquals() {
        Allele a = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        Allele altA = new Allele("http://alt.immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        Allele differentAccession = new Allele("http://immunogenomics.org/allele/0", "A12345", "HLA-A*01:01:01:01", locus);
        Allele differentGlstring = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:02N", locus);
        Allele sameA = new Allele("http://immunogenomics.org/allele/0", "A01234", "HLA-A*01:01:01:01", locus);
        Allele b = new Allele("http://immunogenomics.org/allele/1", "A12345", "HLA-B*01:01:01:01", locus);

        assertFalse(a.equals(null));
        assertFalse(a.equals(new Object()));
        assertTrue(a.equals(a));
        assertFalse(a.equals(b));
        assertFalse(a.equals(altA));
        assertFalse(a.equals(differentAccession));
        assertFalse(a.equals(differentGlstring));
        assertTrue(a.equals(sameA));
    }
}
