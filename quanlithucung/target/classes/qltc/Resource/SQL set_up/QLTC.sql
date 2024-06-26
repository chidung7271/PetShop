create database QLTC
GO
use QLTC
GO
create table sanpham(
	MASP	int primary key identity,
	LOAISP nvarchar(50),
	TENSP	nvarchar(50) not null,
	SL	int,
	GIABAN int,
	XUATXU nvarchar(50),
	MOTA nvarchar(50) ,
);
GO
create table mua_sanpham(
	MADAT int primary key identity,
	MASP int,
	MAKH int,
	CHIPHI int,
	NGAY nvarchar(50),
	
	)
GO

create table dichvuchamsoc(
MADV char(10) primary key,
MASPA char(10),
MATH char(10)
)

GO
create table dichvutrongho(
	MATH int primary key identity,
	MACHUONG int,
	NGNHAN nvarchar(50),
	NGTRA nvarchar(50),
	MATC int,
	CHIPHI int,
	)
GO
create table dichvuspa(
MASPA char(10) primary key,
MAKH char(10),
NGAYSPA date,
MATC char(10),
CHIPHI int,
)
GO
create table cacchuong(
	MACHUONG int primary key identity NOT NULL,
	TT bit ,
	)
GO
create table thucung(
MAKH int,
MATC int primary key identity,
LOAI nvarchar(50),
TEN nvarchar(50),
GIOI_TINH nvarchar(50),
NOTE nvarchar(50),
NGAY_NHAN nvarchar(50),
NGAY_TRA nvarchar(50),
MACHUONG  int,
AVA varbinary(max),
GIATIEN int,
)
GO
create table khachhang(
MAKH int primary key identity,
TEN nvarchar(50),
GIOI_TINH nvarchar(50),
SDT char(11),)
ALTER TABLE mua_sanpham
ADD Constraint fk_DDH_MaKH Foreign Key(MaKH) references khachhang(MaKH) on update cascade on delete cascade,Constraint fk_CTDH_MaHH Foreign Key(MASP) references 
sanpham(MASP) on update cascade on delete cascade

SELECT kh.MAKH,TEN,TENSP,CHIPHI,NGAY 
FROM khachhang kh
INNER JOIN mua_sanpham msp ON kh.MAKH = msp.MAKH Inner join sanpham sp on sp.MASP = msp.MASP
INSERT INTO cacchuong(TT)
VALUES (0),
       (0),
       (0),
	   (0),
	   (0),
	   (0),
	   (0),
	   (0),
	   (0),
	   (0),
	   (0),
	   (0);
SELECT NGAY, SUM(TotalCost) AS total_quantity
FROM (
    SELECT NGAY, CHIPHI AS TotalCost
    FROM mua_sanpham
    UNION ALL
    SELECT NGAY_NHAN, GiATIEN
    FROM thucung
) AS combined_data
GROUP BY NGAY
ORDER BY NGAY;


SELECT CONVERT(DATE, NGAY, 103) AS NGAY, SUM(TotalCost) AS total_quantity
FROM (
    SELECT NGAY, CHIPHI AS TotalCost
    FROM mua_sanpham
    UNION ALL
    SELECT NGAY_NHAN, GiATIEN
    FROM thucung
) AS combined_data
GROUP BY CONVERT(DATE, NGAY, 103)
ORDER BY CONVERT(DATE, NGAY, 103) 



