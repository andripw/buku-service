package com.test.pendaftaranbuku.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "buku", schema = "test")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Buku implements Serializable {

    private static final long serialVersionUID = 4355245150594975046L;

    @Id
    @Column(name = "id_buku", nullable = false, columnDefinition = "text default ('A' || to_char(nextval('test.special_seq'), 'FM0000'))")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "judul")
    private String judul;

    @Column(name = "pengarang")
    private String pengarang;

    @Column(name = "penerbit")
    private String penerbit;

    @Column(name = "tanggal_terbit")
    private Date tanggalTerbit;

    @Column(name = "tebal_buku")
    private String tebalBuku;

    @Column(name = "status_buku", nullable = false)
    private String statusBuku;

    @Column(name = "peminjam")
    private String peminjam;

    @Column(name = "tanggal_pinjam")
    private Date tanggalPinjam;

    @Column(name = "tanggal_kembali")
    private Date tanggalKembali;

    @Column(name = "tanggal_buat", nullable = false)
    private Date tanggalBuat;

    @Column(name = "tanggal_ubah")
    private Date tanggalUbah;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Buku buku = (Buku) o;
        return getId() != null && Objects.equals(getId(), buku.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
