CREATE PROCEDURE uspUpdateUserRole
	-- Add the parameters for the stored procedure here
	@role NVARCHAR(15),
	@email NVARCHAR(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
Update Users
SET [Role] = @role WHERE Email = @email
END
GO