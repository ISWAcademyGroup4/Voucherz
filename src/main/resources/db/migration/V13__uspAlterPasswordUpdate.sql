ALTER PROCEDURE [dbo].[uspUpdateUserPassword]
@password NVARCHAR(50) = NULL,
@email NVARCHAR(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

UPDATE Users
SET [Password] = isNULL(@password,[Password]) WHERE Email = @email
END