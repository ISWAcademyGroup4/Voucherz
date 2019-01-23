CREATE TABLE [dbo].[Users](
	[UserId] [int] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](50) NOT NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](100) NULL,
	[Email] [nvarchar](50) NOT NULL,
	[PhoneNumber] [nvarchar](50) NOT NULL,
	[CompanySize] [int] NOT NULL,
	[Role] [nvarchar](10) NOT NULL,
 )

 GO

 CREATE PROCEDURE [dbo].[uspCreateUser2]
 @FirstName nVARCHAR(50),
 @LastName nVARCHAR(50),
 @Email nVARCHAR(50),
 @Password nVARCHAR(100),
 @PhoneNumber VARCHAR(20),
 @CompanySize int,
 @Role nVarchar(10)
 AS
 BEGIN TRANSACTION
 DECLARE @id int = NULL
 DECLARE @UserId int = NULL
 DECLARE @RoleId int =NULL

 BEGIN
 	--select @RoleId = id from roles where name = 'USER_ROLE'
 	select @id = UserId from Users where email = @email
 	if (@id > 0)
 	begin
 	UPDATE Users set FirstName = @FirstName, LastName = @LastName, [Password]=@password, PhoneNumber=@PhoneNumber, CompanySize=@CompanySize where UserId = @id
 	end
 	Else
 	begin
 	insert into users(Firstname, LastName,phonenumber, Email,[Password], companysize, [role] ) Values (@firstname, @lastname,@phonenumber, @email,@Password, @companysize, @role)
 	Select @UserId=SCOPE_IDENTITY()
 	--insert into User_role (Userid, Roleid) values (@UserId, @RoleId)
 	end
 	END
 commit

 GO

 CREATE PROCEDURE [dbo].[uspFindAllUser]
 AS
     SET NOCOUNT ON
     SELECT * FROM [dbo].[Users]

 	RETURN @@Error

GO

CREATE PROCEDURE [dbo].[uspFindUser]
    (
        @Email nvarchar(50)
    )
AS
    SET NOCOUNT ON
    SELECT * FROM [dbo].[Users] WHERE Email = @Email

    RETURN @@Error

GO

CREATE PROCEDURE [dbo].[uspUserUpdate]
@id BIGINT,
@firstname NVARCHAR(50) = NULL,
@lastname NVARCHAR(50) = NULL,
@password NVARCHAR(50) = NULL,
@email NVARCHAR(50) = NULL,
@phonenumber NVARCHAR(50) = NULL,
@companysize INT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

UPDATE Users
SET FirstName = isNULL(@firstname,FirstName ), LastName = isNuLL(@lastname,LastName), [Password] = isNULL(@password,[Password]), Email = isNULL(@email,Email), PhoneNumber = isNULL(@phonenumber,PhoneNumber), CompanySize = isNULL(@companysize,CompanySize)
WHERE (UserId = @id)
END
GO
