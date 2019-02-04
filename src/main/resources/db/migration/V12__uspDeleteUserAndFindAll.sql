CREATE PROCEDURE uspDeleteUser
	@email NVARCHAR(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

   DELETE FROM Users WHERE Email = @email
END

GO

CREATE PROCEDURE [dbo].[uspGetUsers]
 @Name nvarchar(50) = NULL

AS
BEGIN
    SET NOCOUNT ON;
	SELECT FirstName,LastName,Email
	FROM [dbo].Users
	WHERE (Firstname LIKE '%' + @Name + '%' OR Lastname LIKE '%' + @Name + '%' OR Email LIKE '%' + @Name + '%') OR @Name = '' OR @Name IS NULL
END