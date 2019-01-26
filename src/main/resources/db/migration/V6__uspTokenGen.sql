CREATE PROCEDURE [dbo].[uspFindUserByToken]
	-- Add the parameters for the stored procedure here
	@token NVARCHAR(50),
	@email NVARCHAR(50)=NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT @email=Email FROM PasswordReset WHERE ResetToken = @token
	SELECT * FROM Users WHERE Email = @email
END
