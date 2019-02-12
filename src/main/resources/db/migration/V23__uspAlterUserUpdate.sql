ALTER PROCEDURE [dbo].[uspUserUpdate]
@email NVARCHAR(50),
@firstName NVARCHAR(50) = NULL,
@lastName NVARCHAR(50) = NULL,
@password NVARCHAR(50) = NULL,
--@email NVARCHAR(50) = NULL,
@phoneNumber NVARCHAR(50) = NULL,
@companySize INT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

UPDATE Users
SET FirstName = isNULL(@firstname,FirstName ), LastName = isNuLL(@lastname,LastName), [Password] = isNULL(@password,[Password]), PhoneNumber = isNULL(@phonenumber,PhoneNumber), CompanySize = isNULL(@companysize,CompanySize)
WHERE (Email = @email)
END